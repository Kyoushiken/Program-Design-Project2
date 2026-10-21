/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

/**
 *
 * @author nicka
 */

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
