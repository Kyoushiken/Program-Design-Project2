package com.mycompany.project2.controller;

import com.mycompany.project2.model.Player;


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
