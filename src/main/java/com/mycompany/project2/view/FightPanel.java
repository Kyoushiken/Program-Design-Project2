package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class FightPanel extends JPanel {

    public FightPanel(GameController controller) {
        setLayout(new FlowLayout());
        add(new JLabel("A monster blocks your path!"));

        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> controller.handleFightChoice(1));

        JButton runButton = new JButton("Run");
        runButton.addActionListener(e -> controller.handleFightChoice(2));

        add(attackButton);
        add(runButton);
    }
}
