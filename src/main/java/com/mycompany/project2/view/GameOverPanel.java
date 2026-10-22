package com.mycompany.project2.view;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class GameOverPanel extends JPanel {

    public GameOverPanel(Runnable onPlayAgain, Runnable onResults, Runnable onExit) {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton playAgain = GameStyle.button("Play again");
        JButton results = GameStyle.button("Results");
        JButton exit = GameStyle.button("Exit");

        playAgain.addActionListener(e -> onPlayAgain.run());
        results.addActionListener(e -> onResults.run());
        exit.addActionListener(e -> onExit.run());

        add(playAgain);
        add(results);
        add(exit);
    }
}
