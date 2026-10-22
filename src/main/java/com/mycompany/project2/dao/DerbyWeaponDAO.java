/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.dao;

import com.mycompany.project2.db.DatabaseManager;
import com.mycompany.project2.model.Weapon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author poloj
 * @author nicka
 */
public class DerbyWeaponDAO implements WeaponDAO {

    private static final String SELECT_BY_NAME_SQL
            = "SELECT name, min_damage, max_damage FROM WEAPONS WHERE name = ?";

    // Finds a weapon by its name
    @Override
    public Weapon findByName(String name) {
        Connection conn = DatabaseManager.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(SELECT_BY_NAME_SQL)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Weapon(
                            rs.getString("name"),
                            rs.getInt("min_damage"),
                            rs.getInt("max_damage"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not load weapon '" + name + "'.", e);
        }

        // No weapon found
        return null;
    }
}