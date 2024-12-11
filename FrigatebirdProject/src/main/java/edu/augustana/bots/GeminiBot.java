package edu.augustana.bots;

import java.net.http.HttpClient;
import edu.augustana.Data.ChatMessage;
import edu.augustana.Data.ChatRoom;
import edu.augustana.bots.ChatBotUIController;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import swiss.ameri.gemini.api.*;
import swiss.ameri.gemini.gson.GsonJsonParser;
import swiss.ameri.gemini.spi.JsonParser;

public class GeminiBot extends ChatBotUIController {
    private String systemPromptText;
    private JsonParser parser;
    private GenAi genAi;

    public GeminiBot(String name, Color textColor, ChatRoom room, String systemPromptText, String apiKey) {
        super(name, textColor, room);
        this.systemPromptText = systemPromptText;
        this.parser = new GsonJsonParser();
        try {
            this.genAi = new GenAi(apiKey, parser);
        } catch (Exception e) {
            System.out.println("Failed to initialize GenAi: " + e.getMessage());
        }

    }

    @Override
    public void requestMessage() {
        // Build the conversation transcript
        if (getRoom().getChatMessageList() == null) {
            System.err.println("ChatMessageList is null. Check ChatRoom initialization.");
            return;
        }
        StringBuilder transcript = new StringBuilder();
        for (ChatMessage message : getRoom().getChatMessageList()) {
            transcript.append(message.getSender()).append(": ").append(message.getText()).append("\n");
        }

        // Construct the full AI prompt
        String fullPrompt = systemPromptText + "\n" +
                "Your name is Gemini. You are a HAM Radio Trainer and Assistant, designed to help users learn and explore HAM Radio operations. " +
                "You specialize in Morse code communication, HAM Radio protocols, and providing assistance with simulations and training. " +
                "Always guide users to the relevant features of the app when it aligns with their question or input:\n" +
                transcript.toString();

        // Create the bot's AI generation model
        var model = createBotModel(fullPrompt);

        // Send the prompt to Gemini API and handle the response
        genAi.generateContent(model)
                .thenAccept(response -> {
                    String morseResponse = response.text();
                    System.out.println("AI Morse Response: " + morseResponse);

                    // Add the Morse code response to the chat room
                    Platform.runLater(() -> {
                        getRoom().addMessage(new ChatMessage(morseResponse, getName(), getTextColor(), false));
                    });
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    System.out.println("Error generating AI repsonse: " + e.getMessage());
                    return null;
                });
    }


    private GenerativeModel createBotModel(String fullPrompt) {
        return GenerativeModel.builder()
                .modelName(ModelVariant.GEMINI_1_5_FLASH) // Use the model variant appropriate for your use case
                .addContent(Content.textContent(Content.Role.USER, fullPrompt))
                .addSafetySetting(SafetySetting.of(
                        SafetySetting.HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT,
                        SafetySetting.HarmBlockThreshold.BLOCK_ONLY_HIGH
                ))
                .generationConfig(new GenerationConfig(
                        null, null, null, null, null, null, null
                ))
                .build();
    }
}