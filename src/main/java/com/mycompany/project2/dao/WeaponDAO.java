package com.mycompany.project2.dao;

import com.mycompany.project2.model.Weapon;


public interface WeaponDAO {

    Weapon findByName(String name);
}
