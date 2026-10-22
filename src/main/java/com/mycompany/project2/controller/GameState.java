package com.mycompany.project2.controller;

public enum GameState {
    START("the start"),
    TOWN_GATE("the town gate"),
    CROSSROAD("the crossroad"),
    RIVER("the river"),
    FOREST("the forest"),
    FIGHT("a fight"),
    GAME_OVER("the end");

    private final String place;

    GameState(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}
