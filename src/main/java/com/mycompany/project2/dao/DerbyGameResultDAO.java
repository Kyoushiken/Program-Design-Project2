package com.mycompany.project2.dao;

import com.mycompany.project2.db.DatabaseManager;
import com.mycompany.project2.model.GameResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DerbyGameResultDAO implements GameResultDAO {

    private static final String INSERT_SQL = 
            "INSERT INTO GAME_RESULTS (player_name, outcome) VALUES (?, ?)";
    
    private static final String SELECT_ALL_SQL =
            "SELECT id, player_name, outcome, played_at FROM GAME_RESULTS "
                + "ORDER BY played_at DESC, id DESC";

    private static final String SELECT_BY_PLAYER_SQL =
        "SELECT id, player_name, outcome, played_at FROM GAME_RESULTS "
            + "WHERE player_name = ? ORDER BY played_at DESC, id DESC";

    private static final String COUNT_BY_OUTCOME_SQL =
        "SELECT outcome, COUNT(*) AS total FROM GAME_RESULTS "
            + "GROUP BY outcome ORDER BY outcome";
    
    
    
    
    @Override
    public void save(String playerName, String outcome) {
        Connection conn = DatabaseManager.getInstance().getConnection();
        
        try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, playerName);
            ps.setString(2, outcome);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Could not save game result.", e);
        }
    }
    
    @Override
    public List<GameResult> findAll() {
        Connection conn = DatabaseManager.getInstance().getConnection();
        
        try (PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
            ResultSet rs = ps.executeQuery()) {
            return readResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Could not load game result.", e);
        }
    }
    
    @Override
    public List<GameResult> findByPlayer(String playerName) {
        Connection conn = DatabaseManager.getInstance().getConnection();
        
        try (PreparedStatement ps = conn.prepareStatement(SELECT_BY_PLAYER_SQL)) {
            ps.setString(1, playerName);
            try (ResultSet rs = ps.executeQuery()) {
                return readResults(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not load results for '" + playerName + "'.", e);
        }
    }
    
    @Override
    public Map<String, Integer> countByOutcome() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        Connection conn = DatabaseManager.getInstance().getConnection();
        
        try (PreparedStatement ps = conn.prepareStatement(COUNT_BY_OUTCOME_SQL);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                counts.put(rs.getString("outcome"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not count results by outcome.", e);
        }
        return counts;
    }
    
    private List<GameResult> readResults(ResultSet rs) throws SQLException {
        List<GameResult> results = new ArrayList<>();
        while(rs.next()) {
            results.add(new GameResult(
                    rs.getInt("id"),
                    rs.getString("player_name"),
                    rs.getString("outcome"),
                    rs.getTimestamp("played_at").toLocalDateTime()
            ));
        }
        return results;
    }
    
            
            
            
 }
