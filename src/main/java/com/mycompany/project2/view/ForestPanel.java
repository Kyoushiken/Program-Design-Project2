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
public class ForestPanel extends ChoicePanel {

    public ForestPanel(GameController controller) {

        // Forest actions
        addChoice("Search for a weapon", () -> controller.searchForest());
        addChoice("Back to the crossroad", () -> controller.backToCrossroad());

        // End the current game
        addChoice("Quit", () -> controller.quitGame());
    }
}