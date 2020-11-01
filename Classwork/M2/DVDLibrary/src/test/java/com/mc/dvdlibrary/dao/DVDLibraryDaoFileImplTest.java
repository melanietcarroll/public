/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.dao;

import com.mc.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.mc.dvdlibrary.dto.DVD;
import java.io.FileWriter;
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
public class DVDLibraryDaoFileImplTest {

    DVDLibraryDao testDao;

    public DVDLibraryDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testlibrary.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoFileImpl(testFile);
    }

    @Test
    public void testAddDVD() throws Exception {
        // Create our method test inputs
        String title = "Cars";
        DVD dvd = new DVD(title);
        dvd.setReleaseDate("2009");
        dvd.setStudio("Pixar");
        dvd.setUserRating("5 stars");

        //  Add the DVD to the DAO
        testDao.addDVD(title, dvd);
        // Get the DVD from the DAO
        DVD retrievedDVD = testDao.searchDVDByTitle(title);

        // Check the data is equal
        assertEquals(dvd.getTitle(),
                retrievedDVD.getTitle(),
                "Checking DVD title.");
        assertEquals(dvd.getReleaseDate(),
                retrievedDVD.getReleaseDate(),
                "Checking DVD release date.");
        assertEquals(dvd.getStudio(),
                retrievedDVD.getStudio(),
                "Checking studio.");
        assertEquals(dvd.getUserRating(),
                retrievedDVD.getUserRating(),
                "Checking user rating.");
    }

    @Test
    public void testAddGetAllDVDs() throws Exception {
        // Create our first DVD
        DVD firstDVD = new DVD("Cars 2");
        firstDVD.setReleaseDate("2012");
        firstDVD.setStudio("Pixar");
        firstDVD.setUserRating("4 stars");

        // Create our second DVD
        DVD secondDVD = new DVD("Cars 3");
        secondDVD.setReleaseDate("2015");
        secondDVD.setStudio("Pixar");
        secondDVD.setUserRating("3 stars");

        // Add both DVDs to the DAO
        testDao.addDVD(firstDVD.getTitle(), firstDVD);
        testDao.addDVD(secondDVD.getTitle(), secondDVD);

        // Retrieve the list of all DVDs within the DAO
        List<DVD> allDVDs = testDao.getAllDVDs();

        // First check the general contents of the list
        assertNotNull(allDVDs, "The list of DVDs must not null");
        assertEquals(2, allDVDs.size(), "List of DVDs should have 2 DVDs.");

        // Then the specifics
        assertTrue(testDao.getAllDVDs().contains(firstDVD),
                "The list of DVDs should include Cars 2.");
        assertTrue(testDao.getAllDVDs().contains(secondDVD),
                "The list of students should include Cars 3.");

    }

    @Test
    public void testRemoveDVD() throws Exception {
        // Create two new DVDs
        DVD firstDVD = new DVD("Monsters Inc");
        firstDVD.setReleaseDate("2012");
        firstDVD.setStudio("Pixar");
        firstDVD.setUserRating("4 stars");

        // Create our second DVD
        DVD secondDVD = new DVD("Finding Nemo");
        secondDVD.setReleaseDate("2015");
        secondDVD.setStudio("Pixar");
        secondDVD.setUserRating("5 stars");

        // Add both to the DAO
        testDao.addDVD(firstDVD.getTitle(), firstDVD);
        testDao.addDVD(secondDVD.getTitle(), secondDVD);

        // remove the first DVD - Monsters Inc
        DVD removedDVD = testDao.removeDVD(firstDVD.getTitle());

        // Check that the correct object was removed.
        assertEquals(removedDVD, firstDVD, "The removed DVD should be Monsters Inc.");

        // Get all the DVDs
        List<DVD> allDVDs = testDao.getAllDVDs();

        // First check the general contents of the list
        assertNotNull(allDVDs, "All DVDs list should be not null.");
        assertEquals(1, allDVDs.size(), "All DVDs should only have 1 DVD.");

        // Then the specifics
        assertFalse(allDVDs.contains(firstDVD), "All DVDs should NOT include Monsters Inc.");
        assertTrue(allDVDs.contains(secondDVD), "All DVDs should include Finding Nemo.");

        // Remove the second DVD
        removedDVD = testDao.removeDVD(secondDVD.getTitle());
        // Check that the correct object was removed.
        assertEquals(removedDVD, secondDVD, "The removed student should be Finding Nemo.");

        // retrieve all of the DVDs again, and check the list.
        allDVDs = testDao.getAllDVDs();

        // Check the contents of the list - it should be empty
        assertTrue(allDVDs.isEmpty(), "The retrieved list of DVDs should be empty.");

        // Try to 'get' both DVDs by their old title - they should be null!
        DVD retrievedDVD = testDao.searchDVDByTitle(firstDVD.getTitle());
        assertNull(retrievedDVD, "Monsters Inc was removed, should be null.");

        retrievedDVD = testDao.searchDVDByTitle(secondDVD.getTitle());
        assertNull(retrievedDVD, "Finding Nemo was removed, should be null.");

    }
    @Test
    public void testEditDVD() throws Exception {
        // Create our method test inputs
        String title = "Labyrinth";
        DVD dvd = new DVD(title);
        dvd.setReleaseDate("1980");
        dvd.setStudio("WB");
        dvd.setUserRating("5 stars");

        //  Add the DVD to the DAO
        testDao.addDVD(title, dvd);
        // Get the DVD from the DAO
        DVD retrievedDVD = testDao.searchDVDByTitle(title);

        //Edit the DVD
        dvd.setReleaseDate("1982");
        dvd.setStudio("WB");
        dvd.setUserRating("7 stars");
        
        //  Add the DVD to the DAO
        testDao.addDVD(title, dvd);
        // Get the DVD from the DAO
        DVD retrievedEditedDVD = testDao.searchDVDByTitle(title);
        
        // Check the data is equal
        assertEquals(dvd.getTitle(),
                retrievedEditedDVD.getTitle(),
                "Checking DVD title.");
        assertEquals(dvd.getReleaseDate(),
                retrievedEditedDVD.getReleaseDate(),
                "Checking DVD release date.");
        assertEquals(dvd.getStudio(),
                retrievedEditedDVD.getStudio(),
                "Checking studio.");
        assertEquals(dvd.getUserRating(),
                retrievedEditedDVD.getUserRating(),
                "Checking user rating.");
    }

}
