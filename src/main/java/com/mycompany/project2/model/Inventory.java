package com.mycompany.project2.model;

import java.util.ArrayList;
import java.util.List;


public class Inventory {

    private final List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
    }

    public boolean hasItem(String item) {
        return items.contains(item);
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}
