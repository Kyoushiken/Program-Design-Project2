/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.project2.controller;

/**
 *
 * @author nicka
 */
import com.mycompany.project2.model.Monster;
import com.mycompany.project2.db.DatabaseManager;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GameControllerTest {

    @Test
    public void guardDeniesEntryWithoutRing() {
        GameController controller = new GameController();
        controller.startGame("NoRingTester" + System.currentTimeMillis());

        // Still at the town gate, no ring yet. Choice 1 = talk to guard.
        controller.handleTownGateChoice(1);

        // Should NOT be let in — state stays at TOWN_GATE, not GAME_OVER.
        assertEquals(GameState.TOWN_GATE, controller.getState());
    }

    @Test
    public void winningFightAwardsRing() {
        GameController controller = new GameController();
        controller.startGame("RingWinner" + System.currentTimeMillis());

        controller.handleTownGateChoice(3);   // leave town -> crossroad
        controller.handleCrossroadChoice(3);  // start fight (creates the default Goblin, state = FIGHT)

        // Swap in a 1-HP monster so the next attack is a guaranteed, deterministic kill
        controller.monster = new Monster("Test Target", 1, 1, 1);

        controller.handleFightChoice(1); // attack

        assertTrue(controller.getPlayer().getInventory().hasItem("Silver Ring"));
    }

    @Test
    public void endingGameSavesResult() throws Exception {
        String playerName = "LoseTester" + System.currentTimeMillis();
        GameController controller = new GameController();
        controller.startGame(playerName);

        // Attacking the guard (choice 2) deals a FIXED 1 damage — no randomness.
        // Player starts at 10 HP, so 10 hits guarantees death deterministically.
        for (int i = 0; i < 10 && controller.getState() != GameState.GAME_OVER; i++) {
            controller.handleTownGateChoice(2);
        }

        assertEquals(GameState.GAME_OVER, controller.getState());

        // Confirm a row was actually written to GAME_RESULTS for this player
        try (PreparedStatement ps = DatabaseManager.getInstance().getConnection()
                .prepareStatement("SELECT COUNT(*) FROM GAME_RESULTS WHERE player_name = ?")) {
            ps.setString(1, playerName);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                assertEquals(1, rs.getInt(1));
            }
        }
    }
}