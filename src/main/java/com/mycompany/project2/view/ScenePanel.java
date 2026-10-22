/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author poloj
 * @author nicka
 */
public class ScenePanel extends JPanel {

    private static final String TITLE = "TOWN'S LOST RING";

    private Image picture;
    private GameState scene = GameState.START;

    public ScenePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(960, 540));
        setBackground(GameStyle.DARK);
        setScene(GameState.START);
    }

    // Changes the current scene
    public void setScene(GameState state) {
        loadScene(state);
        repaint();
    }

    // Loads the image for the selected scene
    private void loadScene(GameState state) {
        scene = state;

        URL url = getClass().getResource(
                "/images/" + state.name().toLowerCase() + ".png");

        picture = (url == null)
                ? null
                : new ImageIcon(url).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paints the dark background

        Graphics2D g2 = (Graphics2D) g.create();

        // Draw the scene image
        if (picture != null) {

            // Keeps pixel art sharp when scaled
            g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

            g2.drawImage(
                    picture,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this);
        }

        // Show the game title on the start screen
        if (scene == GameState.START) {
            drawCentered(g2, TITLE, 52f, 150);

        } else if (picture == null) {

            // Show the scene name if no image exists
            drawCentered(
                    g2,
                    scene.name().replace('_', ' '),
                    40f,
                    getHeight() / 2);
        }

        g2.dispose();
    }

    /**
     * Gold text with a dark shadow, so it stays readable on any picture.
     */
    private void drawCentered(Graphics2D g2, String text, float size, int y) {
        g2.setFont(GameStyle.FONT.deriveFont(Font.BOLD, size));

        int x =
                (getWidth()
                - g2.getFontMetrics().stringWidth(text))
                / 2;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 3, y + 3);

        g2.setColor(GameStyle.GOLD);
        g2.drawString(text, x, y);
    }
}
