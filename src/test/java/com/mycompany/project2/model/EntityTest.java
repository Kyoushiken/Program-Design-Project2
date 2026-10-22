package com.mycompany.project2.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

// Simplified with assistance from Claude (Anthropic).
public class EntityTest {

    @Test
    public void hpNeverFallsBelowZero() {
        Monster monster = new Monster("Test", 5, 1, 1);
        monster.takeDamage(100);
        assertEquals(0, monster.getHp());
        assertFalse(monster.isAlive());
    }

    @Test
    public void hpNeverExceedsMaximum() {
        Monster monster = new Monster("Test", 5, 1, 1);
        monster.takeDamage(2);
        monster.heal(100);
        assertEquals(5, monster.getHp());
    }
}
