/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.model;

/**
 *
 * @author poloj
 */

import java.time.LocalDateTime;

public class GameResult {
    private final int id;
    private final String playerName;
    private final String outcome;
    private final LocalDateTime playedAt;
    
    public GameResult(int id, String playerName, String outcome, LocalDateTime playedAt) {
        this.id = id;
        this.playerName = playerName;
        this.outcome = outcome;
        this.playedAt = playedAt;
    }
    
    public int getId() { return id; }
    public String getPlayerName() { return playerName; }
    public String getOutcome() { return outcome; }
    public LocalDateTime getLocalDateTime() { return playedAt; }
    
    @Override
    public String toString(){
        return playerName + " | " + outcome + " | " + playedAt;
    }
}
