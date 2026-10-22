package com.mycompany.project2.view;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class ChoicePanel extends JPanel {

    protected ChoicePanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
    }

    protected void addChoice(String text, Runnable action) {
        JButton button = GameStyle.button(text);
        button.addActionListener(e -> action.run());
        add(button);
    }
}
