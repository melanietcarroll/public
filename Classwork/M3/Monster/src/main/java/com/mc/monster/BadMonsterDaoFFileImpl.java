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
public class BadMonsterDaoFFileImpl implements MonsterDao{
    private final String ROSTER_FILE;
    
    public BadMonsterDaoFFileImpl(){
    ROSTER_FILE = "roster.txt";
}

public BadMonsterDaoFFileImpl(String rosterTextFile){
    ROSTER_FILE = rosterTextFile;
}
    
    Map<Integer, Monster> monsters = new HashMap<>();

    @Override
    public Monster addMonster(int id, Monster m) {
        Monster otherM = monsters.get(id);
        monsters.put(id, m);
        return otherM; //returning a monster if it has an id instead of the created monster
    }

    @Override
    public Monster getMonster(int id) {
        Monster storedMonster = null;
        
        if (monsters.containsKey(id)) {
            storedMonster = monsters.put(id, storedMonster); 
        }

        return storedMonster;
    }

    @Override
    public List<Monster> getAllMonsters() {
        List<Monster> monsterList = new ArrayList<>(monsters.values());
        for (int id : monsters.keySet()) {
            monsterList.add(monsters.get(id));
        }
        return monsterList;
    }

    @Override
    public void updateMonster(int id, Monster m) {
        Monster monster = monsters.get(id);
        if (monster != null) {
            monsters.put(id, m);
        }
    }

    @Override
    public Monster removeMonster(int id) {
        Monster removedMonster = monsters.remove(id);
        return removedMonster;
    }

}
