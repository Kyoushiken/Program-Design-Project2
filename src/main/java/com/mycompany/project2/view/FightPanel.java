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
public class FightPanel extends ChoicePanel {

    public FightPanel(GameController controller) {

        // Fight actions
        addChoice("Attack", () -> controller.attackMonster());
        addChoice("Run", () -> controller.runAway());

        // End the current game
        addChoice("Quit", () -> controller.quitGame());
    }
}