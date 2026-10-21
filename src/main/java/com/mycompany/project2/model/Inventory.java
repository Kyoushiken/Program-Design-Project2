/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.model;


 
import java.util.ArrayList;
import java.util.List;


 //*
 //* @author poloj

public class Inventory {
    
    private List<String> items;
    
    public Inventory() {
        this.items = new ArrayList<>();
    }
    
    public void addItem(String item){
        items.add(item);
    }
    
    public boolean hasItem(String item){
        return items.contains(item);
    }
    
    public void display(){
        if(items.isEmpty()){
            System.out.println(" (empty)");
            return;
        }
        for (String item : items){
            System.out.println(" - " + item);
        }
    }
    
    public java.util.List<String> getItems(){
        return items;
    }
}