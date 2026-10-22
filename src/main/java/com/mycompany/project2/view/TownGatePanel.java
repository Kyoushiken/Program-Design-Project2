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
public class TownGatePanel extends ChoicePanel {

    public TownGatePanel(GameController controller) {

        // Town gate actions
        addChoice("Talk to the guard", () -> controller.talkToGuard());
        addChoice("Attack the guard", () -> controller.attackGuard());
        addChoice("Leave the town", () -> controller.leaveTown());

        // End the current game
        addChoice("Quit", () -> controller.quitGame());
    }
}