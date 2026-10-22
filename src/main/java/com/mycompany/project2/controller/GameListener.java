/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.controller;

import com.mycompany.project2.model.Player;

/**
 *
 * @author poloj
 * @author nicka
 */
public interface GameListener {

    /**
     * The game moved to a new place.
     */
    void stateChanged(GameState newState);

    /**
     * There is a new message to show the player.
     */
    void messageLogged(String message);

    /**
     * The player's HP, weapon or items changed.
     */
    void playerChanged(Player player);

    /**
     * Something went wrong (for example a database error).
     */
    void errorOccurred(String message);
}