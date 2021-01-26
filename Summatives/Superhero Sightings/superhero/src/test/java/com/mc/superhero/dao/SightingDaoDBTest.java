/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.dao;

import com.mc.superhero.entities.Location;
import com.mc.superhero.entities.Organization;
import com.mc.superhero.entities.Sighting;
import com.mc.superhero.entities.Superhero;
import com.mc.superhero.entities.Superpower;
import java.time.LocalDateTime;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created 1/25/21
 * @author Melanie Carroll
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SightingDaoDBTest {
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperheroDao superheroDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    public SightingDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocationById(location.getId());
        }
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }
        
        List<Superhero> superheros = superheroDao.getAllSuperheros();
        for(Superhero superhero : superheros) {
            superheroDao.deleteSuperheroById(superhero.getId());
        }
       
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }
 
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSightingById method, of class SightingDaoDB.
     */
    @Test
    public void testAddAndGetSighting() {
        List<Superhero> superheros = superheroDao.getAllSuperheros();
        for(Superhero superhero : superheros) {
            superheroDao.deleteSuperheroById(superhero.getId());
        }
        
         List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }
        
        LocalDateTime date= LocalDateTime.now();
        
        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location.setLatitude("TestLat");
        location.setLongitude("TestLong");
        location = locationDao.addLocation(location);
        
        Superpower superpower = new Superpower();
        superpower.setName("fast");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(superpowers);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        
        
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
        organization.setSuperheros(superheroDao.getAllSuperheros());
        organization = organizationDao.addOrganization(organization);
        
        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of getAllSightings method, of class SightingDaoDB.
     */
    @Test
    public void testGetAllSightings() {
    }

    /**
     * Test of addSighting method, of class SightingDaoDB.
     */
    @Test
    public void testAddSighting() {
    }

    /**
     * Test of updateSighting method, of class SightingDaoDB.
     */
    @Test
    public void testUpdateSighting() {
    }

    /**
     * Test of deleteSightingById method, of class SightingDaoDB.
     */
    @Test
    public void testDeleteSightingById() {
    }

    /**
     * Test of getLocationForSighting method, of class SightingDaoDB.
     */
    @Test
    public void testGetLocationForSighting() {
    }

    /**
     * Test of getSuperheroForSighting method, of class SightingDaoDB.
     */
    @Test
    public void testGetSuperheroForSighting() {
    }

    /**
     * Test of getSightingsForSuperheroAndLocationByDate method, of class SightingDaoDB.
     */
    @Test
    public void testGetSightingsForSuperheroAndLocationByDate() {
    }
    
}
