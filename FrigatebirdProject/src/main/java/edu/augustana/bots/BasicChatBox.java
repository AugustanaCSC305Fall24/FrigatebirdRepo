//package edu.augustana.bots;
//
//import javafx.scene.paint.Color;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Random;
//
//public class BasicChatBox extends Chatbot{
//
//
//    private static final Random randomGen = new Random();
//
//    public BasicChatBot(String name, Color textColor, ChatRoom room) {
//        super(name, textColor, room);
//    }
//
//    @Override
//    public String toString() {
//        return getName() + " [" + getPersonalityType() + "]";
//    }
//
//    abstract String getPersonalityType();
//
//    /** Ask this bot to generate some message and send it to the chat room. */
//    public void requestMessage() {
//        String messageText;
//        ChatMessage previousMessage = getRoom().getChatMessageList().getLast();
//        if (randomGen.nextDouble() < 0.5 || previousMessage.isBold() ) {
//            if (randomGen.nextBoolean()) {
//                messageText = generateResponseToContent(previousMessage.getText());
//            } else {
//                messageText = generateResponseToSender(previousMessage.getSender());
//            }
//        } else {
//            messageText = generateNewMessageText();
//        }
//        getRoom().addMessage(new ChatMessage(messageText, getName(), getTextColor(), false));
//    }
//
//    abstract String generateNewMessageText();
//
//    String getRandomTopic() {
//        String[] topics = {"the weather", "Star Wars", "your mother", "the best vacation spot",
//                "life's treasures", "classes you've failed", "the world's worst CSC 305 professor",
//                "the best way to cook pasta", "Lana wrestling gators", "defining your aura",
//                "who won the 2020 election", "the best way to make a grilled cheese sandwich",
//                "banning Tik Tok", "death metal bands", "organized crime", "your hair"};
//        return topics[randomGen.nextInt(topics.length)];
//    }
//
//    private String generateResponseToContent(String incomingMessage) {
//        return generateCommentAbout(selectRandomLongWord(incomingMessage));
//    }
//    private String selectRandomLongWord(String incomingMessage) {
//        String[] words = incomingMessage.split("\\W+");
//        if (words.length > 0) {
//            Arrays.sort(words, Comparator.comparingInt(String::length)); // sort by length
//            // randomly pick a longer word (from the last 1/6 of the list)
//            return words[randomGen.nextInt(words.length * 5 / 6, words.length)];
//        } else {
//            return "nothing";
//        }
//    }
//
//    abstract String generateCommentAbout(String word);
//
//    abstract String generateResponseToSender(String sender);
//
//    public static String getRandomBotName() {
//        String[] names = {"Alice", "Bubba", "Candy", "Doodles", "Egbert", "Fifi", "Gus", "Holly", "Iggy",
//                "Jasper", "Kiki", "Lulu", "Mimi", "Noodles", "Oscar", "Penny", "Quincy", "Rufus", "Sally",
//                "Toby", "Ursula", "Violet", "Wally", "Xander", "Yolanda", "Zelda"};
//        String[] adjectives = {"Awesome", "Bodacious", "Clunker", "Dude", "Eery", "Funky", "Goosey", "Happy",
//                "Hippy", "Irritable", "Jolly", "Kooky", "Lunker", "Messy", "Nut", "Optometrist", "Punky",
//                "Quirky", "Rumpled", "Snarky", "Tree", "Unknown", "Vixen", "Wonk", "Xenial", "Yummy",
//                "Zany"};
//        String name =names[randomGen.nextInt(names.length)];
//        String adjective = adjectives[randomGen.nextInt(adjectives.length)];
//        return  name + " the " + adjective;
//    }
//
//
//
//
//}
