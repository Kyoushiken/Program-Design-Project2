package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class CrossroadPanel extends JPanel {

    public CrossroadPanel(GameController controller) {
        setLayout(new FlowLayout());
        add(new JLabel("At the crossroad:"));
        addChoiceButton("North - river (heal)", 1, controller);
        addChoiceButton("East - forest (find weapon)", 2, controller);
        addChoiceButton("West - danger (fight)", 3, controller);
        addChoiceButton("South - back to town", 4, controller);
        addChoiceButton("Quit", 0, controller);
    }

    private void addChoiceButton(String text, int choice, GameController controller) {
        JButton button = new JButton(text);
        button.addActionListener(e -> controller.handleCrossroadChoice(choice));
        add(button);
    }
}
