package com.mycompany.project2.view;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    public GameOverPanel() {
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Game Over — see the log above for your result. Close the window to exit.");
        add(label);
    }
}
