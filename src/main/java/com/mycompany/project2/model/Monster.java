package com.mycompany.project2.model;

import java.util.Random;


public class Monster extends Entity {

    private static final Random RANDOM = new Random();

    private final int minDamage;
    private final int maxDamage;

    public Monster(String name, int hp, int minDamage, int maxDamage) {
        super(name, hp);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    /**
     * Polymorphism
     */
    @Override
    public int attack() {
        return RANDOM.nextInt(maxDamage - minDamage + 1) + minDamage;
    }
}
