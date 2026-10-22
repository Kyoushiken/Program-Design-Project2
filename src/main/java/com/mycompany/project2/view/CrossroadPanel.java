package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;


public class CrossroadPanel extends ChoicePanel {

    public CrossroadPanel(GameController controller) {
        addChoice("North: River", () -> controller.goNorth());
        addChoice("East: Forest", () -> controller.goEast());
        addChoice("West: Danger", () -> controller.goWest());
        addChoice("South: Town", () -> controller.goSouth());
        addChoice("Quit", () -> controller.quitGame());
    }
}
