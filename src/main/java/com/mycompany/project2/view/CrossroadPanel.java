/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;

/**
 *
 * @author poloj
 * @author nicka
 */
public class CrossroadPanel extends ChoicePanel {

    public CrossroadPanel(GameController controller) {

        // Navigation choices
        addChoice("North: River", () -> controller.goNorth());
        addChoice("East: Forest", () -> controller.goEast());
        addChoice("West: Danger", () -> controller.goWest());
        addChoice("South: Town", () -> controller.goSouth());

        // End the current game
        addChoice("Quit", () -> controller.quitGame());
    }
}