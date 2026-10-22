package com.mycompany.project2.model;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

// Simplified with assistance from Claude (Anthropic).
public class WeaponTest {

    @Test
    public void rollDamageStaysWithinRange() {
        Weapon sword = new Weapon("Sword", 2, 8);
        for (int i = 0; i < 1000; i++) {
            int damage = sword.rollDamage();
            assertTrue("Damage out of range: " + damage,
                    damage >= sword.getMinDamage() && damage <= sword.getMaxDamage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void minimumDamageAboveMaximumIsRejected() {
        new Weapon("Broken", 5, 1);
    }
}
