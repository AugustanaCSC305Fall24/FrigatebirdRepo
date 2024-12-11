package edu.augustana.bots;
import edu.augustana.Data.ChatMessage;
import edu.augustana.Data.ChatRoom;
import javafx.scene.paint.Color;

import java.util.*;

public abstract class ChatBotUIController {
    private final String name;
    private final Color textColor;
    private final ChatRoom room;

    private static final Random randomGen = new Random();

    public ChatBotUIController(String name, Color textColor, ChatRoom room) {
        this.name = name;
        this.textColor = textColor;
        this.room = room;
    }

    public String getName() {
        return name;
    }
    public Color getTextColor() {
        return textColor;
    }
    public ChatRoom getRoom() {
        return room;
    }

    /** Ask this bot to generate some message and send it to the chat room. */
    public abstract void requestMessage();

    public static String getRandomBotName() {
        String[] names = {"Alice", "Bubba", "Candy", "Doodles", "Egbert", "Fifi", "Gus", "Holly", "Iggy",
                "Jasper", "Kiki", "Lulu", "Mimi", "Noodles", "Oscar", "Penny", "Quincy", "Rufus", "Sally",
                "Toby", "Ursula", "Violet", "Wally", "Xander", "Yolanda", "Zelda"};
        String[] adjectives = {"Awesome", "Bodacious", "Clunker", "Dude", "Eery", "Funky", "Goosey", "Happy",
                "Hippy", "Irritable", "Jolly", "Kooky", "Lunker", "Messy", "Nut", "Optometrist", "Punky",
                "Quirky", "Rumpled", "Snarky", "Tree", "Unknown", "Vixen", "Wonk", "Xenial", "Yummy",
                "Zany"};
        String name =names[randomGen.nextInt(names.length)];
        String adjective = adjectives[randomGen.nextInt(adjectives.length)];
        return  name + " the " + adjective;
    }
}
