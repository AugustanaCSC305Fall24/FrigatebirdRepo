module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens edu.augustana to javafx.fxml;
    exports edu.augustana;
    exports edu.augustana.sound;
    opens edu.augustana.sound to javafx.fxml;
    exports edu.augustana.practiceMode;
    opens edu.augustana.practiceMode to javafx.fxml;
}
