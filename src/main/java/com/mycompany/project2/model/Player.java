/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.model;

import com.mycompany.project2.model.Entity;

/**
 *
 * @author poloj
 */
public class Player extends Entity {
     
    private Weapon weapon;
    private Inventory inventory;
    
    public Player(String name, int hp, Weapon weapon) {
        super(name, hp);
        this.weapon = weapon;
        this.inventory = new Inventory();
        inventory.addItem(weapon.getName());
        
    }
    
    @Override //POLYMORPHISM
    public int attack() {
        return weapon.rollDamage();   
    }
    
    public Weapon getWeapon(){return weapon;}
    
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
        inventory.addItem(weapon.getName());
    }
    
    public Inventory getInventory(){
        return inventory;
    }
    
}
