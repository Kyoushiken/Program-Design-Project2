package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(GameController controller) {
        setLayout(new FlowLayout());

        JTextField nameField = new JTextField(15);
        JButton startButton = new JButton("Start Adventure");

        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name.");
                return;
            }
            controller.startGame(name);
        });

        add(new JLabel("Enter your name:"));
        add(nameField);
        add(startButton);
    }
}
