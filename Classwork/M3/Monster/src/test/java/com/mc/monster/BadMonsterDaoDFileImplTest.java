/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.monster;

import static com.mc.monster.MonsterType.SWAMPTHING;
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
public class BadMonsterDaoDFileImplTest {
    
    MonsterDao testDao = new BadMonsterDaoDFileImpl();
    
    public BadMonsterDaoDFileImplTest() {
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
    public void testAddTwoGetMonsters() {
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
        
        // Create our method test inputs
        int idTwo = 2;
        Monster monsterTwo = new Monster("Larry", YETI, 2, "ice cream");

        //add monster to dao
        testDao.addMonster(idTwo, monsterTwo);
        // Get monster from the DAO
        Monster retrievedMonsterTwo = testDao.getMonster(idTwo);

        // Check the data is equal
        assertEquals(monsterTwo.getName(),
                retrievedMonsterTwo.getName(),
                "Checking monster name.");
        assertEquals(monsterTwo.getType(),
                retrievedMonsterTwo.getType(),
                "Checking monster type.");
        assertEquals(monsterTwo.getPeopleEaten(),
                retrievedMonsterTwo.getPeopleEaten(),
                "Checking number of people eaten.");
        assertEquals(monsterTwo.getFavoriteFood(),
                retrievedMonsterTwo.getFavoriteFood(),
                "Checking favorite food.");
    }
    @Test
    public void testAddUpdateTwoMonsters() {

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
        
        // Create our method test inputs
        int idTwo = 2;
        Monster monsterTwo = new Monster("Larry", YETI, 2, "ice cream");

        //add monster to dao
        testDao.addMonster(idTwo, monsterTwo);
        // Get all monsters
        List<Monster> twoMonsters = testDao.getAllMonsters();

        // First check the general contents of the list
        assertNotNull(twoMonsters, "The list of monsters must not null");
        assertEquals(2, twoMonsters.size(), "List of monsters should have 2 monsters.");

        // edit the 2nd monster - Larry
        monsterTwo.setName("Ned");
        monsterTwo.setType(SWAMPTHING);
        monsterTwo.setPeopleEaten(55);
        monsterTwo.setFavoriteFood("snakes");
        // Check that the object was updated.
        testDao.updateMonster(idTwo, monsterTwo);

        // Get monster from the DAO
        Monster retrievedNextMonster = testDao.getMonster(idTwo);

        // Check the data is equal
        assertEquals(monsterTwo.getName(),
                retrievedNextMonster.getName(),
                "Checking monster name.");
        assertEquals(monsterTwo.getType(),
                retrievedNextMonster.getType(),
                "Checking monster type.");
        assertEquals(monsterTwo.getPeopleEaten(),
                retrievedNextMonster.getPeopleEaten(),
                "Checking number of people eaten.");
        assertEquals(monsterTwo.getFavoriteFood(),
                retrievedNextMonster.getFavoriteFood(),
                "Checking favorite food.");

    

    }
}
