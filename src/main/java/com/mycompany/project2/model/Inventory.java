/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author poloj
 * @author nicka
 */
public class Inventory {

    private final List<String> items = new ArrayList<>();

    // Adds an item to the inventory
    public void addItem(String item) {
        items.add(item);
    }

    // Checks if an item is in the inventory
    public boolean hasItem(String item) {
        return items.contains(item);
    }

    // Returns a copy of the inventory items
    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}