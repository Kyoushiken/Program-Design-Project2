/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.model;

/**
 *
 * @author poloj
 * @author nicka
 */
public abstract class Entity {

    private final String name;
    private final int maxHp;
    private int hp;

    public Entity(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    // Calculates attack damage
    public abstract int attack();

    // Reduces health when damage is taken
    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    // Restores health
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    // Checks if the entity is still alive
    public boolean isAlive() {
        return hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public String toString() {
        return name + " (HP: " + hp + "/" + maxHp + ")";
    }
}