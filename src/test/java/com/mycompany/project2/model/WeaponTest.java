/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.model;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests the Weapon class behaviour.
 *
 * @author poloj
 * @author nicka
 */
public class WeaponTest {

    // Tests that weapon damage stays within the valid range
    @Test
    public void rollDamageStaysWithinRange() {
        Weapon sword = new Weapon("Sword", 2, 8);

        for (int i = 0; i < 1000; i++) {
            int damage = sword.rollDamage();

            assertTrue(
                    "Damage out of range: " + damage,
                    damage >= sword.getMinDamage()
                    && damage <= sword.getMaxDamage());
        }
    }

    // Tests that an invalid damage range is rejected
    @Test(expected = IllegalArgumentException.class)
    public void minimumDamageAboveMaximumIsRejected() {
        new Weapon("Broken", 5, 1);
    }
}