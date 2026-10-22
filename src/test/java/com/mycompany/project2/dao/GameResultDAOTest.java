package com.mycompany.project2.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.mycompany.project2.model.GameResult;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Test;


public class GameResultDAOTest {

    private final GameResultDAO dao = new DerbyGameResultDAO();
    private final String playerName = "TestPlayer" + System.nanoTime();

    @After
    public void removeTestResults() {
        dao.deleteByPlayer(playerName);
    }

    @Test
    public void savedResultCanBeReadBackWithAllFieldsIntact() {
        dao.save(playerName, "WON - test");

        List<GameResult> found = dao.findByPlayer(playerName);

        assertEquals(1, found.size());
        assertEquals(playerName, found.get(0).getPlayerName());
        assertEquals("WON - test", found.get(0).getOutcome());
        assertNotNull(found.get(0).getPlayedAt());
    }

    @Test
    public void outcomeCountsAreCorrect() {
        String outcome = "TEST OUTCOME " + System.nanoTime(); // unique, so no other row matches
        dao.save(playerName, outcome);
        dao.save(playerName, outcome);

        Map<String, Integer> counts = dao.countByOutcome();

        assertEquals(2, (int) counts.get(outcome));
    }
}
