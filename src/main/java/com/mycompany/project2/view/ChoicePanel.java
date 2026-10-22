/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author poloj
 * @author nicka
 */
public abstract class ChoicePanel extends JPanel {

    // Sets up the panel layout
    protected ChoicePanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
    }

    // Adds a button choice to the panel
    protected void addChoice(String text, Runnable action) {
        JButton button = GameStyle.button(text);
        button.addActionListener(e -> action.run());
        add(button);
    }
}