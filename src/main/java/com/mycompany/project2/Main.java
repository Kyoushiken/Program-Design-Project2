/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2;

import com.mycompany.project2.view.GameStyle;
import com.mycompany.project2.view.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author poloj
 * @author nicka
 */
public class Main {

    // Starts the game application
    public static void main(String[] args) {

        // Create the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            GameStyle.install();
            new MainFrame().setVisible(true);
        });
    }
}
