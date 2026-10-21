package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class TownGatePanel extends JPanel {

    public TownGatePanel(GameController controller) {
        setLayout(new FlowLayout());
        add(new JLabel("At the town gate:"));
        addChoiceButton("Talk to the guard", 1, controller);
        addChoiceButton("Attack the guard", 2, controller);
        addChoiceButton("Leave the town", 3, controller);
        addChoiceButton("Quit", 0, controller);
    }

    private void addChoiceButton(String text, int choice, GameController controller) {
        JButton button = new JButton(text);
        button.addActionListener(e -> controller.handleTownGateChoice(choice));
        add(button);
    }
}
