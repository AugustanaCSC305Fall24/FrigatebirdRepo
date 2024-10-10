import java.awt.*;
//javafx
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sound {
    //Solyana Kifle

//    Button shortAudio = new Button();
//    Button longAudio = new Button();
        // Constructor to create the GUI components
    public Sound() {
            // Create a new frame (window)
        JFrame frame = new JFrame("Dash Button Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a text area where the dashes will appear
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

            // Create a button
        JButton button = new JButton("Insert Dashes");

            // Add action listener to handle button click
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    // When button is clicked, append a long dash and a small dash to the text area
                    textArea.append("—-");
                }
            });

            // Add the text area and button to the frame
        frame.getContentPane().add(textArea, "Center");
        frame.getContentPane().add(button, "South");

            // Make the frame visible
        frame.setVisible(true);
    }
}






