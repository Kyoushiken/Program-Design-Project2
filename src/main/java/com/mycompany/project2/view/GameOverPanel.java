/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

/**
 *
 * @author nicka
 */

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    public GameOverPanel() {
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Game Over — see the log above for your result. Close the window to exit.");
        add(label);
    }
}
