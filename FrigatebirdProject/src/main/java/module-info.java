module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires tyrus.standalone.client;
    requires com.google.gson;
    requires swiss.ameri.gemini.api;
    requires swiss.ameri.gemini.gson;

    opens edu.augustana to javafx.fxml, com.google.gson;
    exports edu.augustana;
    exports edu.augustana.sound;
    opens edu.augustana.sound to javafx.fxml;
    exports edu.augustana.practiceMode;
    opens edu.augustana.practiceMode to javafx.fxml;
    opens edu.augustana.bots to javafx.fxml;
}
