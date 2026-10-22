/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Displays the start screen for the game.
 *
 * @author poloj
 * @author nicka
 */
public class StartPanel extends JPanel {

    private static final int MAX_NAME_LENGTH = 20;

    public StartPanel(GameController controller) {

        // Set up the panel layout
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JTextField nameField = new JTextField(15);
        nameField.setFont(GameStyle.FONT);

        JButton startButton = GameStyle.button("Start Adventure");

        // Start the game when the button is clicked
        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();

            // Check that the name length is valid
            if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a name of 1 to "
                        + MAX_NAME_LENGTH
                        + " characters.");
                return;
            }

            controller.startGame(name);
        });

        // Pressing Enter also starts the game
        nameField.addActionListener(e -> startButton.doClick());

        add(GameStyle.label("Your name:"));
        add(nameField);
        add(startButton);
    }
}