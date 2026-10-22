/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.mycompany.project2.model.GameResult;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author poloj
 * @author nicka
 */
// Generated with assistance from Claude (Anthropic).
public class GameResultDAOTest {

    private final GameResultDAO dao = new DerbyGameResultDAO();
    private final String playerName = "TestPlayer" + System.nanoTime();

    // Removes test data after each test
    @After
    public void removeTestResults() {
        dao.deleteByPlayer(playerName);
    }

    // Tests that a saved result can be loaded correctly
    @Test
    public void savedResultCanBeReadBackWithAllFieldsIntact() {
        dao.save(playerName, "WON - test");

        List<GameResult> found = dao.findByPlayer(playerName);

        assertEquals(1, found.size());
        assertEquals(playerName, found.get(0).getPlayerName());
        assertEquals("WON - test", found.get(0).getOutcome());
        assertNotNull(found.get(0).getPlayedAt());
    }

    // Tests that outcome totals are counted correctly
    @Test
    public void outcomeCountsAreCorrect() {

        // Unique outcome so other records will not affect the test
        String outcome = "TEST OUTCOME " + System.nanoTime();

        dao.save(playerName, outcome);
        dao.save(playerName, outcome);

        Map<String, Integer> counts = dao.countByOutcome();

        assertEquals(2, (int) counts.get(outcome));
    }
}