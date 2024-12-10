package edu.augustana.bots;

import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ChatBotUIController {
//    private final String name;
//    private final Color textColor;
//    private final ChatRoom room;

    private static final Random randomGen = new Random();




    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea chatBot;

    @FXML
    private TextArea textArea;

    @FXML
    void initialize() {
        assert chatBot != null : "fx:id=\"chatBot\" was not injected: check your FXML file 'ChatBot.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'ChatBot.fxml'.";

    }




//    public ChatBot(String name, Color textColor, ChatRoom room) {
//        this.name = name;
//        this.textColor = textColor;
//        this.room = room;
//    }

//    public String getName() {
//
//        return name;
//    }
//
//    public Color getTextColor() {
//        return textColor;
//    }
//    //public ChatRoom getRoom()
//        return room;



    public static String getRandomBotName() {
    String[] names = {"Alice", "Bubba", "Candy", "Doodles", "Egbert", "Fifi", "Gus", "Holly", "Iggy",
            "Jasper", "Kiki", "Lulu", "Mimi", "Noodles", "Oscar", "Penny", "Quincy", "Rufus", "Sally",
            "Toby", "Ursula", "Violet", "Wally", "Xander", "Yolanda", "Zelda"};
    String[] adjectives = {"Awesome", "Bodacious", "Clunker", "Dude", "Eery", "Funky", "Goosey", "Happy",
            "Hippy", "Irritable", "Jolly", "Kooky", "Lunker", "Messy", "Nut", "Optometrist", "Punky",
            "Quirky", "Rumpled", "Snarky", "Tree", "Unknown", "Vixen", "Wonk", "Xenial", "Yummy",
            "Zany"};
    String name = names[randomGen.nextInt(names.length)];
    String adjective = adjectives[ChatBotUIController.randomGen.nextInt(adjectives.length)];
    return name + " the " + adjective;

    }
}

