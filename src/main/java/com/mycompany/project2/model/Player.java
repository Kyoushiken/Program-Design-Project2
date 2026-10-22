package com.mycompany.project2.model;


public class Player extends Entity {

    private Weapon weapon;
    private final Inventory inventory = new Inventory();

    public Player(String name, int hp, Weapon weapon) {
        super(name, hp);
        this.weapon = weapon;
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

    
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        if (!inventory.hasItem(weapon.getName())) {
            inventory.addItem(weapon.getName());
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
