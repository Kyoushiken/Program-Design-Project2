/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.dao;

import com.mycompany.project2.model.GameResult;
import java.util.List;
import java.util.Map;

/**
 *
 * @author poloj
 * @author nicka
 */
public interface GameResultDAO {

    void save(String playerName, String outcome);

    List<GameResult> findAll();

    List<GameResult> findByPlayer(String playerName);

    Map<String, Integer> countByOutcome();

    int deleteByPlayer(String playerName);

    int deleteAll();
}