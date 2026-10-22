package com.mycompany.project2.model;

import java.util.Random;


public class Weapon {

    private static final Random RANDOM = new Random();

    private final String name;
    private final int minDamage;
    private final int maxDamage;

    public Weapon(String name, int minDamage, int maxDamage) {
        if (minDamage > maxDamage) {
            throw new IllegalArgumentException("minDamage cannot be greater than maxDamage");
        }
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int rollDamage() {
        return RANDOM.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public String getName() {
        return name;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}
