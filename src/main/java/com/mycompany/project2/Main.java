package com.mycompany.project2;

import com.mycompany.project2.view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Swing components must be created/updated on the Event Dispatch
        // Thread, not whatever thread main() happens to run on.
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
//project2