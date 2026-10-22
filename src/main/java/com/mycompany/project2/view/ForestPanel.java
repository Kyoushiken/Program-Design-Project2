package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;


public class ForestPanel extends ChoicePanel {

    public ForestPanel(GameController controller) {
        addChoice("Search for a weapon", () -> controller.searchForest());
        addChoice("Back to the crossroad", () -> controller.backToCrossroad());
        addChoice("Quit", () -> controller.quitGame());
    }
}
