/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.db;

/**
 *
 * @author poloj
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class DatabaseManager {

    private static final String DB_NAME = "project2db";
  
    private static final String CONNECTION_URL = "jdbc:derby:" + DB_NAME + ";create=true";

    private static DatabaseManager instance;

    private final Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
            createSchemaIfNeeded();
            seedDataIfEmpty();
        } catch (SQLException e) {
            throw new RuntimeException("Could not initialise embedded Derby database.", e);
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void createSchemaIfNeeded() throws SQLException {
        if (!tableExists("WEAPONS")) {
            try (Statement st = connection.createStatement()) {
                st.execute(
                    "CREATE TABLE WEAPONS (" +
                    "    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "    name VARCHAR(50) NOT NULL," +
                    "    min_damage INT NOT NULL," +
                    "    max_damage INT NOT NULL" +
                    ")"
                );
            }
        }

        if (!tableExists("MONSTERS")) {
            try (Statement st = connection.createStatement()) {
                st.execute(
                    "CREATE TABLE MONSTERS (" +
                    "    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "    name VARCHAR(50) NOT NULL," +
                    "    hp INT NOT NULL," +
                    "    min_damage INT NOT NULL," +
                    "    max_damage INT NOT NULL" +
                    ")"
                );
            }
        }

        if (!tableExists("GAME_RESULTS")) {
            try (Statement st = connection.createStatement()) {
                st.execute(
                    "CREATE TABLE GAME_RESULTS (" +
                    "    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "    player_name VARCHAR(50) NOT NULL," +
                    "    outcome VARCHAR(200) NOT NULL," +
                    "    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")"
                );
            }
        }
    }

    private boolean tableExists(String tableName) throws SQLException {
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
            return rs.next();
        }
    }

    private void seedDataIfEmpty() throws SQLException {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM WEAPONS")) {
            rs.next();
            if (rs.getInt(1) == 0) {
                st.execute("INSERT INTO WEAPONS (name, min_damage, max_damage) VALUES ('Knife', 1, 5)");
                st.execute("INSERT INTO WEAPONS (name, min_damage, max_damage) VALUES ('Long Sword', 2, 8)");
            }
        }
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM MONSTERS")) {
            rs.next();
            if (rs.getInt(1) == 0) {
                st.execute("INSERT INTO MONSTERS (name, hp, min_damage, max_damage) VALUES ('Goblin', 15, 1, 4)");
            }
        }
    }

    /** Call once on application shutdown (e.g. in a WindowListener's windowClosing). */
    public void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:" + DB_NAME + ";shutdown=true");
        } catch (SQLException e) {
      
        }
    }
}
