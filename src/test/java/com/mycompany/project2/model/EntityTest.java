/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 * Tests the Entity class behaviour.
 *
 * @author poloj
 * @author nicka
 */
public class EntityTest {

    // Tests that health cannot go below zero
    @Test
    public void hpNeverFallsBelowZero() {
        Monster monster = new Monster("Test", 5, 1, 1);

        monster.takeDamage(100);

        assertEquals(0, monster.getHp());
        assertFalse(monster.isAlive());
    }

    // Tests that health cannot exceed the maximum value
    @Test
    public void hpNeverExceedsMaximum() {
        Monster monster = new Monster("Test", 5, 1, 1);

        monster.takeDamage(2);
        monster.heal(100);

        assertEquals(5, monster.getHp());
    }
}