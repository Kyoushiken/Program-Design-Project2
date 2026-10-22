/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;

/**
 * Displays the choices available at the river.
 *
 * @author poloj
 * @author nicka
 */
public class RiverPanel extends ChoicePanel {

    public RiverPanel(GameController controller) {

        // River actions
        addChoice("Drink water (heal)", () -> controller.drinkWater());
        addChoice("Back to the crossroad", () -> controller.backToCrossroad());

        // End the current game
        addChoice("Quit", () -> controller.quitGame());
    }
}