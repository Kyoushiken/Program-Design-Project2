/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.controller;

/**
 *
 * @author poloj
 * @author nicka
 */
public enum GameState {

    START("the start"),
    TOWN_GATE("the town gate"),
    CROSSROAD("the crossroad"),
    RIVER("the river"),
    FOREST("the forest"),
    FIGHT("a fight"),
    GAME_OVER("the end");

    // Description of the current location
    private final String place;

    GameState(String place) {
        this.place = place;
    }

    // Returns the location name
    public String getPlace() {
        return place;
    }
}