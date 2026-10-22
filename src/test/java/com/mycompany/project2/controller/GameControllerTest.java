/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.mycompany.project2.dao.DerbyGameResultDAO;
import com.mycompany.project2.dao.GameResultDAO;
import com.mycompany.project2.model.Monster;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author poloj
 * @author nicka
 */
// Generated with assistance from Claude (Anthropic).
public class GameControllerTest {

    private final GameResultDAO resultDAO = new DerbyGameResultDAO();
    private GameController controller;
    private String playerName;

    // Creates a new game before each test
    @Before
    public void startNewGame() {
        controller = new GameController();
        playerName = "Tester" + System.nanoTime();
        controller.startGame(playerName);
    }

    // Removes test data after each test
    @After
    public void removeTestResults() {
        resultDAO.deleteByPlayer(playerName);
    }

    // Helper method to win a fight and get the ring
    private void winFightForRing() {
        controller.leaveTown();
        controller.startFightWith(new Monster("Test Target", 1, 1, 1));
        controller.attackMonster(); // weapon always deals at least 1 damage
    }

    // Tests that the guard blocks entry without the ring
    @Test
    public void guardDeniesEntryWithoutRing() {
        controller.talkToGuard();
        assertEquals(GameState.TOWN_GATE, controller.getState());
    }

    // Tests that winning a fight gives the ring
    @Test
    public void winningFightAwardsRing() {
        winFightForRing();
        assertTrue(controller.getPlayer().getInventory().hasItem("Silver Ring"));
    }

    // Tests that the guard allows entry with the ring
    @Test
    public void guardLetsPlayerInWithRing() {
        winFightForRing();
        controller.goSouth();
        controller.talkToGuard();
        assertEquals(GameState.GAME_OVER, controller.getState());
    }

    // Tests that the game result is saved
    @Test
    public void endingGameSavesResult() {
        for (int i = 0; i < 10
                && controller.getState() != GameState.GAME_OVER; i++) {
            controller.attackGuard();
        }

        assertEquals(GameState.GAME_OVER, controller.getState());
        assertEquals(1, resultDAO.findByPlayer(playerName).size());
    }
}