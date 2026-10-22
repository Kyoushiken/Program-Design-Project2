package com.mycompany.project2.model;

import java.util.Random;


public class MonsterFactory {

    private static final Random RANDOM = new Random();

    private MonsterFactory() {
    }

    public static Monster createGoblin() {
        return new Monster("Goblin", 15, 1, 4);
    }

    public static Monster createWolf() {
        return new Monster("Wolf", 12, 1, 3);
    }

    public static Monster createSlime() {
        return new Monster("Slime", 8, 1, 2);
    }

    
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
