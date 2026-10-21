package com.mycompany.project2.dao;

import com.mycompany.project2.model.Weapon;
import java.util.List;


public interface WeaponDAO {
    List<Weapon> findAll();
    Weapon findByName(String name);
}
