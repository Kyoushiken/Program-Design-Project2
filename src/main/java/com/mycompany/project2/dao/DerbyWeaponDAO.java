package com.mycompany.project2.dao;

import com.mycompany.project2.db.DatabaseManager;
import com.mycompany.project2.model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DerbyWeaponDAO implements WeaponDAO {

    @Override
    public List<Weapon> findAll() {
        List<Weapon> weapons = new ArrayList<>();
        String sql = "SELECT name, min_damage, max_damage FROM WEAPONS";
        Connection conn = DatabaseManager.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                weapons.add(new Weapon(
                    rs.getString("name"),
                    rs.getInt("min_damage"),
                    rs.getInt("max_damage")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not load weapons from database.", e);
        }
        return weapons;
    }

    @Override
    public Weapon findByName(String name) {
        String sql = "SELECT name, min_damage, max_damage FROM WEAPONS WHERE name = ?";
        Connection conn = DatabaseManager.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Weapon(
                        rs.getString("name"),
                        rs.getInt("min_damage"),
                        rs.getInt("max_damage")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not load weapon '" + name + "' from database.", e);
        }
        return null;
    }
}
