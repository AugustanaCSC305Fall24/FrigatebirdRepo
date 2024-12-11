package edu.augustana.bots;

import edu.augustana.Data.ChatMessage;
import edu.augustana.Data.ChatRoom;
import edu.augustana.bots.ChatBotUIController;
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
        this.genAi = new GenAi(apiKey, parser);
    }

    @Override
    public void requestMessage() {
        // Build the conversation transcript
        StringBuilder transcript = new StringBuilder();
        for (ChatMessage message : getRoom().getChatMessageList()) {
            transcript.append(message.getSender()).append(": ").append(message.getText()).append("\n");
        }

        // Construct the full AI prompt
        String fullPrompt = systemPromptText + "\n" +
                "Your name is Gemini. You are a HAM Radio Trainer and Assistant, designed to help users learn and explore HAM Radio operations. " +
                "You specialize in Morse code communication, HAM Radio protocols, and providing assistance with simulations and training. " +
                "Here are the key features of this app that you can reference to assist users: " +
                "1. Morse Code Training: Helps users learn and practice Morse code. " +
                "2. Disaster Simulations: Engages users in simulated scenarios to practice HAM Radio communication during emergencies. " +
                "3. Practice Modes: Allows users to test their skills in various HAM Radio exercises. " +
                "4. Scenario Builder: Enables users to create custom HAM Radio scenarios. " +
                "5. AI Chat Support: Provides answers to HAM Radio questions and guidance for using the app. " +
                "6. Frequency Tuning: Simulates the process of tuning to different frequencies. " +
                "7. Message Translation: Converts between Morse code and English for learning and practice. " +
                "If a user asks a question about Morse code, respond in Morse code. For other queries, reply in English. " +
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
                    getRoom().addMessage(new ChatMessage(morseResponse, getName(), getTextColor(), false));
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    System.out.println("AI Response Error: " + e.getMessage());
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