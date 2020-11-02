/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.monster;

import static com.mc.monster.MonsterType.VAMPIRE;
import static com.mc.monster.MonsterType.WEREWOLF;
import static com.mc.monster.MonsterType.YETI;
import com.mc.monster.dto.Monster;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class BadMonsterDaoAFleImplTest {

    MonsterDao testDao = new AGoodMonsterDaoFileImpl();

    public BadMonsterDaoAFleImplTest() {
    }

    @Test
    public void testAddGetMonster() {
        // Create our method test inputs
        int id = 1;
        Monster monsterOne = new Monster("Harry", YETI, 2, "ice pop");

        //add monster to dao
        testDao.addMonster(id, monsterOne);
        // Get monster from the DAO
        Monster retrievedMonster = testDao.getMonster(id);

        // Check the data is equal
        assertEquals(monsterOne.getName(),
                retrievedMonster.getName(),
                "Checking monster name.");
        assertEquals(monsterOne.getType(),
                retrievedMonster.getType(),
                "Checking monster type.");
        assertEquals(monsterOne.getPeopleEaten(),
                retrievedMonster.getPeopleEaten(),
                "Checking number of people eaten.");
        assertEquals(monsterOne.getFavoriteFood(),
                retrievedMonster.getFavoriteFood(),
                "Checking favorite food.");
    }

    @Test
    public void testAddGetAllMonsters() {
        //create first monster

        int idOne = 1;
        Monster monsterOne = new Monster("Harry", YETI, 2, "ice pop");

        //create second monster
        int idTwo = 2;
        Monster monsterTwo = new Monster("Edward", VAMPIRE, 100, "garlic breadsticks");

        // Add both monsters to the DAO
        testDao.addMonster(idOne, monsterOne);
        testDao.addMonster(idTwo, monsterTwo);

        // Retrieve the list of all monsters within the DAO
        List<Monster> allMonsters = testDao.getAllMonsters();

        // First check the general contents of the list
        assertNotNull(allMonsters, "The list of monsters must not null");
        assertEquals(2, allMonsters.size(), "List of monsters should have 2 monsters.");

        // Then the specifics
        assertTrue(testDao.getAllMonsters().contains(monsterOne),
                "The list of monsters should include Harry.");
        assertTrue(testDao.getAllMonsters().contains(monsterTwo),
                "The list of monsters should include Edward.");
    }
    
    @Test
    public void testAddRemoveMonster() {
        // Create two new monsters
        int idOne = 1;
        Monster monsterOne = new Monster("Harry", YETI, 2, "ice pop");

        //create second monster
        int idTwo = 2;
        Monster monsterTwo = new Monster("Edward", VAMPIRE, 100, "garlic breadsticks");

        // Add both monsters to the DAO
        testDao.addMonster(idOne, monsterOne);
        testDao.addMonster(idTwo, monsterTwo);

        // remove the first monster - Harry
        Monster removedMonster = testDao.removeMonster(idOne);
        // Check that the correct object was removed.
        assertEquals(removedMonster, monsterOne, "The removed monster should be Harry.");

        // Get all monsters
        List<Monster> allMonsters = testDao.getAllMonsters();

        // First check the general contents of the list
        assertNotNull(allMonsters, "The list of monsters must not null");
        assertEquals(1, allMonsters.size(), "List of monsters should have 1 monster.");

        // Then the specifics
        assertFalse(allMonsters.contains(monsterOne), "All monsters should NOT include Harry.");
        assertTrue(allMonsters.contains(monsterTwo), "All monsters should  include Edward.");

        // remove the second monster - Edward
        removedMonster = testDao.removeMonster(idTwo);
        // Check that the correct object was removed.
        assertEquals(removedMonster, monsterTwo, "The removed monster should be Edward.");
        // Get all monsters
        allMonsters = testDao.getAllMonsters();

        // Check the contents of the list - it should be empty
        assertTrue(allMonsters.isEmpty(), "The retrieved list of monsters should be empty.");

        // Try to 'get' both monsters by their old id - they should be null!
        Monster retrievedMonster = testDao.getMonster(idOne);
        assertNull(retrievedMonster, "Harry was removed, should be null.");

        retrievedMonster = testDao.getMonster(idTwo);
        assertNull(retrievedMonster, "Edward was removed, should be null.");

    }
    @Test
    public void testAddUpdateMonster() {

        // Create our method test inputs
        int id = 1;
        Monster monsterOne = new Monster("Harry", YETI, 2, "ice pop");

        //add monster to dao
        testDao.addMonster(id, monsterOne);
        // Get all monsters
        List<Monster> allMonsters = testDao.getAllMonsters();

        // First check the general contents of the list
        assertNotNull(allMonsters, "The list of monsters must not null");
        assertEquals(1, allMonsters.size(), "List of monsters should have 1 monster.");

        // edit the first monster - Harry
        monsterOne.setName("Larry");
        monsterOne.setType(WEREWOLF);
        monsterOne.setPeopleEaten(10);
        monsterOne.setFavoriteFood("cats");
        // Check that the object was updated.
        testDao.updateMonster(id, monsterOne);

        // Get monster from the DAO
        Monster retrievedMonster = testDao.getMonster(id);

        // Check the data is equal
        assertEquals(monsterOne.getName(),
                retrievedMonster.getName(),
                "Checking monster name.");
        assertEquals(monsterOne.getType(),
                retrievedMonster.getType(),
                "Checking monster type.");
        assertEquals(monsterOne.getPeopleEaten(),
                retrievedMonster.getPeopleEaten(),
                "Checking number of people eaten.");
        assertEquals(monsterOne.getFavoriteFood(),
                retrievedMonster.getFavoriteFood(),
                "Checking favorite food.");

    }
}
