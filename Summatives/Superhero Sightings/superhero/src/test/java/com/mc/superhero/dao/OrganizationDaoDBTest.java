/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.dao;

import com.mc.superhero.entities.Organization;
import com.mc.superhero.entities.Location;
import com.mc.superhero.entities.Sighting;
import com.mc.superhero.entities.Superhero;
import com.mc.superhero.entities.Superpower;
import java.time.LocalDateTime;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
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
public class OrganizationDaoDBTest {
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
    
    public OrganizationDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @BeforeEach
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
     * Test of getOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddAndGetOrganization() {
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
        List<Superhero> superheros = superheroDao.getAllSuperheros();
        
//        Sighting sighting = new Sighting();
//        sighting.setDate(date);
//        sighting.setLocation(location);
//        sighting.setSuperhero(superhero);
//        sighting = sightingDao.addSighting(sighting);
        
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
        organization.setSuperheros(superheros);
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        fromDao.setSuperheros(superheros);
        
        assertEquals(organization, fromDao);
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
    }

    /**
     * Test of addOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddOrganization() {
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
    }

    /**
     * Test of getSuperherosForOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetSuperherosForOrganization() {
    }
    
}
