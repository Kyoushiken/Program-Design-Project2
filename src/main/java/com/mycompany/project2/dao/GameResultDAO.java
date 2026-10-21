package com.mycompany.project2.dao;

import com.mycompany.project2.model.GameResult;

import java.util.List;
import java.util.Map;

public interface GameResultDAO {
    
    void save(String playerName, String outcome);
    
    /** Results, new first **/
    List<GameResult> findAll();
    
    /** Results for one player, new first **/
    List<GameResult> findByPlayer(String playerName);
    
    /**No. of Games per outcome **/
    Map<String, Integer> countByOutcome();
}
