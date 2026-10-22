package com.mycompany.project2.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class GameStyle {

    static final Color GOLD = new Color(0xC89B3C);
    static final Color DARK = new Color(0x1E1E2E);
    static final Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, 14);

    private GameStyle() {
    }

    public static void install() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            
        }
    }

    static JButton button(String text) {
        JButton button = new JButton(text);
        button.setFont(FONT.deriveFont(Font.BOLD, 14f));
        button.setBackground(GOLD);
        button.setForeground(DARK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARK, 2),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(FONT);
        label.setForeground(Color.WHITE);
        return label;
    }

    static JPanel shade(LayoutManager layout) {
        JPanel panel = new JPanel(layout) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 170));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setOpaque(false);
        return panel;
    }
}
