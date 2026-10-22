/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.controller;

import com.mycompany.project2.dao.DerbyGameResultDAO;
import com.mycompany.project2.dao.DerbyWeaponDAO;
import com.mycompany.project2.dao.GameResultDAO;
import com.mycompany.project2.dao.WeaponDAO;
import com.mycompany.project2.db.DatabaseManager;
import com.mycompany.project2.model.GameResult;
import com.mycompany.project2.model.Monster;
import com.mycompany.project2.model.MonsterFactory;
import com.mycompany.project2.model.Player;
import com.mycompany.project2.model.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author poloj
 * @author nicka
 */
public class GameController {

    private static final int START_HP = 10;
    private static final String SILVER_RING = "Silver Ring";
    private static final String LONG_SWORD = "Long Sword";

    // Stores game listeners
    private final List<GameListener> listeners = new ArrayList<>();

    // Database access objects
    private final WeaponDAO weaponDAO = new DerbyWeaponDAO();
    private final GameResultDAO resultDAO = new DerbyGameResultDAO();

    private GameState state = GameState.START;
    private Player player;
    private Monster monster;

    // Adds a listener to receive game updates
    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

    // Updates the current game state
    private void setState(GameState newState) {
        state = newState;
        listeners.forEach(l -> l.stateChanged(newState));
    }

    // Sends messages to the game log
    private void log(String message) {
        listeners.forEach(l -> l.messageLogged(message));
    }

    // Updates player information
    private void notifyPlayerChanged() {
        listeners.forEach(l -> l.playerChanged(player));
    }

    // Reports database errors
    private void reportError(String message) {
        listeners.forEach(l -> l.errorOccurred(message));
    }

    public GameState getState() {
        return state;
    }

    public Player getPlayer() {
        return player;
    }

    // Starts a new game
    public void startGame(String playerName) {
        try {
            player = new Player(playerName, START_HP, loadWeapon("Knife"));
        } catch (RuntimeException e) {
            reportError("Could not start a new game - database error: " + e.getMessage());
            return;
        }

        log("Welcome, " + playerName + ". You stand before the sealed gates of the town.");
        setState(GameState.TOWN_GATE);
    }

    // Resets the game
    public void restart() {
        player = null;
        monster = null;
        setState(GameState.START);
    }

    // Ends the game as a quit
    public void quitGame() {
        endGame("QUIT at " + state.getPlace());
    }

    // Saves the result and finishes the game
    private void endGame(String outcome) {
        try {
            resultDAO.save(player.getName(), outcome);
        } catch (RuntimeException e) {
            reportError("Could not save your result - database error: " + e.getMessage());
        }

        log(outcome);
        setState(GameState.GAME_OVER);
    }

    // ---------- Town gate ----------

    // Talks to the guard
    public void talkToGuard() {
        if (player.getInventory().hasItem(SILVER_RING)) {
            log("Guard: You defeated the monster! Welcome to our town!");
            endGame("WON - entered the town");
        } else {
            log("Guard: Sorry, strangers cannot enter.");
        }
    }

    // Attacks the guard
    public void attackGuard() {
        player.takeDamage(1);
        log("Guard: Don't be foolish! You take 1 damage. " + player);

        notifyPlayerChanged();

        if (!player.isAlive()) {
            endGame("LOSE - defeated by the guard");
        }
    }

    // Leaves the town gate
    public void leaveTown() {
        log("You leave the town and follow the road to a crossroad.");
        setState(GameState.CROSSROAD);
    }

    // ---------- Crossroad ----------

    // Goes north to the river
    public void goNorth() {
        log("You follow the path north to the river.");
        setState(GameState.RIVER);
    }

    // Goes east to the forest
    public void goEast() {
        log("You walk east into the forest.");
        setState(GameState.FOREST);
    }

    // Goes west and starts a fight
    public void goWest() {
        startFightWith(MonsterFactory.createRandom());
    }

    // Returns to the town gate
    public void goSouth() {
        log("You walk south, back to the town gate.");
        setState(GameState.TOWN_GATE);
    }

    // Returns to the crossroad
    public void backToCrossroad() {
        log("You return to the crossroad.");
        setState(GameState.CROSSROAD);
    }

    // ---------- River and forest ----------

    // Restores some health
    public void drinkWater() {
        player.heal(1);
        log("You drink the cool water and feel refreshed.");
        notifyPlayerChanged();
    }

    // Searches for a weapon in the forest
    public void searchForest() {
        if (player.getInventory().hasItem(LONG_SWORD)) {
            log("You search the forest again but find nothing new.");
            return;
        }

        try {
            player.setWeapon(loadWeapon(LONG_SWORD));
            log("You found a Long Sword and equipped it!");
            notifyPlayerChanged();
        } catch (RuntimeException e) {
            reportError("Could not get the weapon - database error: " + e.getMessage());
        }
    }

    // ---------- Fight ----------

    // Starts a battle with a monster
    void startFightWith(Monster newMonster) {
        monster = newMonster;
        log("A wild " + monster.getName() + " appears!");
        setState(GameState.FIGHT);
    }

    // Attacks the monster
    public void attackMonster() {
        int damageDealt = player.attack();
        monster.takeDamage(damageDealt);

        log("You hit the " + monster.getName() + " for " + damageDealt + " damage. " + monster);

        if (!monster.isAlive()) {
            winFight();
            return;
        }

        int damageTaken = monster.attack();
        player.takeDamage(damageTaken);

        log("The " + monster.getName() + " hits you for " + damageTaken + " damage.");

        notifyPlayerChanged();

        if (!player.isAlive()) {
            endGame("LOSE - killed by a " + monster.getName());
        }
    }

    // Escapes from battle
    public void runAway() {
        log("You ran away from the fight!");
        setState(GameState.CROSSROAD);
    }

    // Rewards the player after winning
    private void winFight() {
        log("You defeated the " + monster.getName() + "! It dropped a silver ring!");

        if (!player.getInventory().hasItem(SILVER_RING)) {
            player.getInventory().addItem(SILVER_RING);
        }

        notifyPlayerChanged();
        setState(GameState.CROSSROAD);
    }

    // ---------- Results screen ----------

    // Returns all saved results
    public List<GameResult> getAllResults() {
        return resultDAO.findAll();
    }

    // Returns results for a specific player
    public List<GameResult> getResultsFor(String playerName) {
        return resultDAO.findByPlayer(playerName);
    }

    // Counts the different game outcomes
    public Map<String, Integer> getOutcomeCounts() {
        return resultDAO.countByOutcome();
    }

    // Deletes all saved results
    public void clearResults() {
        resultDAO.deleteAll();
    }

    // Closes the database connection
    public void shutdown() {
        DatabaseManager.shutdown();
    }

    // Loads a weapon from the database
    private Weapon loadWeapon(String name) {
        Weapon weapon = weaponDAO.findByName(name);

        if (weapon == null) {
            throw new IllegalStateException("Weapon '" + name + "' is not in the database.");
        }

        return weapon;
    }
}