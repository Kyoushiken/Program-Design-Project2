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
public class GameOverPanel extends JPanel {

    public GameOverPanel(Runnable onPlayAgain, Runnable onResults, Runnable onExit) {

        // Set up the panel layout
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton playAgain = GameStyle.button("Play again");
        JButton results = GameStyle.button("Results");
        JButton exit = GameStyle.button("Exit");

        // Button actions
        playAgain.addActionListener(e -> onPlayAgain.run());
        results.addActionListener(e -> onResults.run());
        exit.addActionListener(e -> onExit.run());

        add(playAgain);
        add(results);
        add(exit);
    }
}