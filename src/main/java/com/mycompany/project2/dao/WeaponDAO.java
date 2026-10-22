/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.dao;

import com.mycompany.project2.model.Weapon;

/**
 *
 * @author poloj
 * @author nicka
 */
public interface WeaponDAO {

    // Finds a weapon by name
    Weapon findByName(String name);
}