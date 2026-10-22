/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.model;

import java.util.Random;

/**
 *
 * @author poloj
 * @author nicka
 */
public class MonsterFactory {

    private static final Random RANDOM = new Random();

    // Prevents objects from being created
    private MonsterFactory() {
    }

    // Creates a Goblin monster
    public static Monster createGoblin() {
        return new Monster("Goblin", 15, 1, 4);
    }

    // Creates a Wolf monster
    public static Monster createWolf() {
        return new Monster("Wolf", 12, 1, 3);
    }

    // Creates a Slime monster
    public static Monster createSlime() {
        return new Monster("Slime", 8, 1, 2);
    }

    // Creates a random monster
    public static Monster createRandom() {
        int pick = RANDOM.nextInt(3);

        if (pick == 0) {
            return createGoblin();
        } else if (pick == 1) {
            return createWolf();
        } else {
            return createSlime();
        }
    }
}