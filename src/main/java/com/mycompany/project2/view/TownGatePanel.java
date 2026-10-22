package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;

public class TownGatePanel extends ChoicePanel {

    public TownGatePanel(GameController controller) {
        addChoice("Talk to the guard", () -> controller.talkToGuard());
        addChoice("Attack the guard", () -> controller.attackGuard());
        addChoice("Leave the town", () -> controller.leaveTown());
        addChoice("Quit", () -> controller.quitGame());
    }
}
