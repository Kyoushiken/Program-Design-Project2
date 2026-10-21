/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

/**
 *
 * @author poloj
 */

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
