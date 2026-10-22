package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StartPanel extends JPanel {

    private static final int MAX_NAME_LENGTH = 20; 

    public StartPanel(GameController controller) {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JTextField nameField = new JTextField(15);
        nameField.setFont(GameStyle.FONT);
        JButton startButton = GameStyle.button("Start Adventure");

        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a name of 1 to " + MAX_NAME_LENGTH + " characters.");
                return;
            }
            controller.startGame(name);
        });
        nameField.addActionListener(e -> startButton.doClick()); // Enter also starts

        add(GameStyle.label("Your name:"));
        add(nameField);
        add(startButton);
    }
}
