package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;


public class FightPanel extends ChoicePanel {

    public FightPanel(GameController controller) {
        addChoice("Attack", () -> controller.attackMonster());
        addChoice("Run", () -> controller.runAway());
        addChoice("Quit", () -> controller.quitGame());
    }
}
