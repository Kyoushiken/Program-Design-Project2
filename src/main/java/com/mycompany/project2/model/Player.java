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
public class Player extends Entity {

    private Weapon weapon;
    private final Inventory inventory = new Inventory();

    public Player(String name, int hp, Weapon weapon) {
        super(name, hp);
        this.weapon = weapon;

        // Add the starting weapon to the inventory
        inventory.addItem(weapon.getName());
    }

    /**
     * Polymorphism
     */
    @Override
    public int attack() {
        return weapon.rollDamage();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    // Changes the player's weapon
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;

        // Add the weapon if it is not already owned
        if (!inventory.hasItem(weapon.getName())) {
            inventory.addItem(weapon.getName());
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}