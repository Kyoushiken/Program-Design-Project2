package com.mycompany.project2.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.mycompany.project2.dao.DerbyGameResultDAO;
import com.mycompany.project2.dao.GameResultDAO;
import com.mycompany.project2.model.Monster;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GameControllerTest {

    private final GameResultDAO resultDAO = new DerbyGameResultDAO();
    private GameController controller;
    private String playerName;

    @Before
    public void startNewGame() {
        controller = new GameController();
        playerName = "Tester" + System.nanoTime();
        controller.startGame(playerName);
    }

    @After
    public void removeTestResults() {
        resultDAO.deleteByPlayer(playerName);
    }

    private void winFightForRing() {
        controller.leaveTown();
        controller.startFightWith(new Monster("Test Target", 1, 1, 1));
        controller.attackMonster(); // the weapon always deals at least 1 damage
    }

    @Test
    public void guardDeniesEntryWithoutRing() {
        controller.talkToGuard();
        assertEquals(GameState.TOWN_GATE, controller.getState());
    }

    @Test
    public void winningFightAwardsRing() {
        winFightForRing();
        assertTrue(controller.getPlayer().getInventory().hasItem("Silver Ring"));
    }

    @Test
    public void guardLetsPlayerInWithRing() {
        winFightForRing();
        controller.goSouth();
        controller.talkToGuard();
        assertEquals(GameState.GAME_OVER, controller.getState());
    }

    @Test
    public void endingGameSavesResult() {
        for (int i = 0; i < 10 && controller.getState() != GameState.GAME_OVER; i++) {
            controller.attackGuard();
        }
        assertEquals(GameState.GAME_OVER, controller.getState());
        assertEquals(1, resultDAO.findByPlayer(playerName).size());
    }
}
