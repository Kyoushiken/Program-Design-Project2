package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;


public class RiverPanel extends ChoicePanel {

    public RiverPanel(GameController controller) {
        addChoice("Drink water (heal)", () -> controller.drinkWater());
        addChoice("Back to the crossroad", () -> controller.backToCrossroad());
        addChoice("Quit", () -> controller.quitGame());
    }
}
