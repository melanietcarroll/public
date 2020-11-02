/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.monster;

import com.mc.monster.dto.Monster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shawn
 */
public class BadMonsterDaoDFileImpl implements MonsterDao{
    Map<Integer, Monster> monsters = new HashMap<>();
    
    @Override
    public Monster addMonster(int id, Monster m) {
        Monster oldM = monsters.put(id, m);
        return oldM;
    }

    @Override
    public Monster getMonster(int id) {
       Monster storedMonster = monsters.get(id);
       return storedMonster;
    }

    @Override
    public List<Monster> getAllMonsters() {
        ArrayList<Monster> manyMonsters = new ArrayList<>();
        for (Monster m : monsters.values()) { 
            manyMonsters.add(m);
        }
        return manyMonsters;
    }

    @Override
    public void updateMonster(int id, Monster m) {
        if(monsters.containsKey(id))
            monsters.remove(id);    //not updating but adding another monster
        monsters.put(id, m);
    }

    @Override
    public Monster removeMonster(int id) {
        Monster removed = monsters.remove(id);
        return removed;
    }
}
