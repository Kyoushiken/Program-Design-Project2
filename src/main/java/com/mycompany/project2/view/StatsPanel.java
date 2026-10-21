/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

/**
 *
 * @author nicka
 */

import com.mycompany.project2.controller.GameController;
import com.mycompany.project2.model.Player;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StatsPanel extends JPanel implements PropertyChangeListener {
    
    private final GameController controller;
    private final JLabel hpLabel = new JLabel("HP: -");
    private final JLabel weaponLabel = new JLabel("Weapon: -");
    private final JLabel inventoryLabel = new JLabel("Inventory: -");
    
    public StatsPanel(GameController controller){
        this.controller = controller;
        controller.addListener(this); 
        
        setLayout(new GridLayout(3,1));
        add(hpLabel);
        add(weaponLabel);
        add(inventoryLabel);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if (GameController.PLAYER_PROPERTY.equals(evt.getPropertyName()) || GameController.STATE_PROPERTY.equals(evt.getPropertyName()))
        {
         refresh();
        }
    }
    
    private void refresh(){
        Player player = controller.getPlayer();
        if (player == null){
            hpLabel.setText("HP: -");
            weaponLabel.setText("Weapon: -");
            inventoryLabel.setText("Inventory: -");
            return;
        }
        hpLabel.setText("HP: " + player.getHp() + " / " + player.getMaxHp());
        weaponLabel.setText("Weapon: " + player.getWeapon().getName());
        inventoryLabel.setText("Inventory: " + String.join(", ", player.getInventory().getItems()));
    }
    
}
