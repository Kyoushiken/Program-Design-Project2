package com.mycompany.project2.db;

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
            createTables();
            addStartingWeapons();
        } catch (SQLException e) {
            throw new RuntimeException("Could not start the embedded Derby database.", e);
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

    private void createTables() throws SQLException {
        createTableIfMissing("WEAPONS",
                "CREATE TABLE WEAPONS ("
                + "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                + "name VARCHAR(50) NOT NULL, "
                + "min_damage INT NOT NULL, "
                + "max_damage INT NOT NULL)");

        createTableIfMissing("GAME_RESULTS",
                "CREATE TABLE GAME_RESULTS ("
                + "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                + "player_name VARCHAR(50) NOT NULL, "
                + "outcome VARCHAR(200) NOT NULL, "
                + "played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    
    private void createTableIfMissing(String tableName, String createSql) throws SQLException {
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
            if (rs.next()) {
                return; // table already exists
            }
        }
        try (Statement st = connection.createStatement()) {
            st.execute(createSql);
        }
    }

    
    private void addStartingWeapons() throws SQLException {
        boolean empty;
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM WEAPONS")) {
            rs.next();
            empty = rs.getInt(1) == 0;
        }
        if (!empty) {
            return;
        }
        try (Statement st = connection.createStatement()) {
            st.execute("INSERT INTO WEAPONS (name, min_damage, max_damage) VALUES ('Knife', 1, 5)");
            st.execute("INSERT INTO WEAPONS (name, min_damage, max_damage) VALUES ('Long Sword', 2, 8)");
        }
    }

    /**
     * Call once when the program closes, so Derby saves everything properly.
     */
    public static synchronized void shutdown() {
        if (instance == null) {
            return; // the database was never opened
        }
        try {
            DriverManager.getConnection("jdbc:derby:" + DB_NAME + ";shutdown=true");
        } catch (SQLException e) {
            
        }
    }
}
