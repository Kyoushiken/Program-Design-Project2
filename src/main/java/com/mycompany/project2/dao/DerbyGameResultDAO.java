/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.dao;

/**
 *
 * @author nicka
 */

import com.mycompany.project2.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DerbyGameResultDAO implements GameResultDAO {

    @Override
    public void save(String playerName, String outcome) {
        String sql = "INSERT INTO GAME_RESULTS (player_name, outcome) VALUES (?, ?)";
        Connection conn = DatabaseManager.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, playerName);
            ps.setString(2, outcome);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Could not save game result.", e);
        }
    }
}
