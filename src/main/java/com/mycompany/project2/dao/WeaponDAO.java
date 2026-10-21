/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.dao;

/**
 *
 * @author nicka
 */

import com.mycompany.project2.model.Weapon;
import java.util.List;


public interface WeaponDAO {
    List<Weapon> findAll();
    Weapon findByName(String name);
}
