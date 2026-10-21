package com.mycompany.project2.controller;

import com.mycompany.project2.dao.DerbyGameResultDAO;
import com.mycompany.project2.dao.DerbyWeaponDAO;
import com.mycompany.project2.dao.GameResultDAO;
import com.mycompany.project2.dao.WeaponDAO;
import com.mycompany.project2.model.Monster;
import com.mycompany.project2.model.Player;
import com.mycompany.project2.model.Weapon;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class GameController {

    public static final String STATE_PROPERTY = "state";
    public static final String LOG_PROPERTY = "log";
    public static final String PLAYER_PROPERTY = "player";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final WeaponDAO weaponDAO = new DerbyWeaponDAO();
    private final GameResultDAO resultDAO = new DerbyGameResultDAO();

    private GameState state = GameState.START;
    private Player player;
    private Monster monster;
    private boolean hasRing = false;

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GameState getState() {
        return state;
    }

    public Player getPlayer() {
        return player;
    }

    public Monster getMonster() {
        return monster;
    }

    /** Call once, when the player submits their name on the start screen. */
    public void startGame(String playerName) {
        Weapon startingWeapon = weaponDAO.findByName("Knife");
        player = new Player(playerName, 10, startingWeapon);
        log("Welcome, " + playerName + "! " + player);
        setState(GameState.TOWN_GATE);
    }

    /** choice: 1 = talk to guard, 2 = attack guard, 3 = leave town, other = quit */
    public void handleTownGateChoice(int choice) {
        switch (choice) {
            case 1 -> {
                if (hasRing) {
                    log("Guard: You defeated the monster! Welcome to our town!");
                    endGame("WON - entered the town");
                } else {
                    log("Guard: Sorry, strangers cannot enter.");
                    // stays in TOWN_GATE — no state change needed
                }
            }
            case 2 -> {
                player.takeDamage(1);
                log("Guard: Don't be foolish! You take 1 damage.\n" + player);
                firePlayerChanged();
                if (!player.isAlive()) {
                    endGame("LOSE - defeated by the guard");
                }
            }
            case 3 -> setState(GameState.CROSSROAD);
            default -> endGame("QUIT at the town gate");
        }
    }

    /** choice: 1 = river (heal), 2 = forest (find sword), 3 = fight, 4 = back to town, other = quit */
    public void handleCrossroadChoice(int choice) {
        switch (choice) {
            case 1 -> {
                player.heal(1);
                log("You drank from the river and feel refreshed. " + player);
                firePlayerChanged();
            }
            case 2 -> {
                Weapon longSword = weaponDAO.findByName("Long Sword");
                player.setWeapon(longSword);
                log("You found a Long Sword and equipped it!");
                firePlayerChanged();
            }
            case 3 -> startFight();
            case 4 -> setState(GameState.TOWN_GATE);
            default -> endGame("QUIT at the crossroad");
        }
    }

    private void startFight() {
        monster = new Monster("Goblin", 15, 1, 4);
        log("A wild " + monster.getName() + " appears!");
        setState(GameState.FIGHT);
    }

    /** choice: 1 = attack, 2 = run */
    public void handleFightChoice(int choice) {
        if (choice == 2) {
            log("You ran away from the fight!");
            setState(GameState.CROSSROAD);
            return;
        }

        int damageDealt = player.attack();
        monster.takeDamage(damageDealt);
        log("You hit the " + monster.getName() + " for " + damageDealt + " damage.");

        if (!monster.isAlive()) {
            log("You defeated the " + monster.getName() + "! It dropped a silver ring!");
            player.getInventory().addItem("Silver Ring");
            hasRing = true;
            firePlayerChanged();
            setState(GameState.CROSSROAD);
            return;
        }

        int damageTaken = monster.attack();
        player.takeDamage(damageTaken);
        log("The " + monster.getName() + " hits you for " + damageTaken + " damage.");
        firePlayerChanged();

        if (!player.isAlive()) {
            endGame("LOSE - killed by a " + monster.getName());
        }
    }

    private void endGame(String outcome) {
        resultDAO.save(player.getName(), outcome);
        log(outcome);
        setState(GameState.GAME_OVER);
    }

    private void setState(GameState newState) {
        GameState old = this.state;
        this.state = newState;
        support.firePropertyChange(STATE_PROPERTY, old, newState);
    }

    private void log(String message) {
        support.firePropertyChange(LOG_PROPERTY, null, message);
    }

    private void firePlayerChanged() {
        support.firePropertyChange(PLAYER_PROPERTY, null, player);
    }
}
