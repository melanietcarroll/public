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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created 1/26/21
 * @author Melanie Carroll
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperpowerDaoDBTest {
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
    
    public SuperpowerDaoDBTest() {
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
     * Test of getSuperpowerById method, of class SuperpowerDaoDB.
     */
    @Test
    public void testAddGetSuperpowerById() {
        LocalDate date = LocalDate.parse("2020-01-08"); 
        LocalTime time = LocalTime.parse("12:32:22", 
            DateTimeFormatter.ISO_TIME);

        
        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location.setLatitude("TestLat");
        location.setLongitude("TestLong");
        location = locationDao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
//        organization.setSuperheros(superheroDao.getAllSuperheros());
        organization = organizationDao.addOrganization(organization);
        List<Organization> orgs = organizationDao.getAllOrganizations();
        
        Superpower superpower = new Superpower();
        superpower.setName("fast");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(superpowers);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);
        superhero.setSightings(sightings);
        
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getId());
        
        
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerDaoDB.
     */
    @Test
    public void testGetAllSuperpowers() {
         List<Location> dblocations = locationDao.getAllLocations();
        for(Location location : dblocations) {
            locationDao.deleteLocationById(location.getId());
        }
        List<Organization> dborganizations = organizationDao.getAllOrganizations();
        for(Organization organization : dborganizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }
        
        List<Superhero> dbsuperheros = superheroDao.getAllSuperheros();
        for(Superhero superhero : dbsuperheros) {
            superheroDao.deleteSuperheroById(superhero.getId());
        }
       
        List<Sighting> dbsightings = sightingDao.getAllSightings();
        for(Sighting sighting : dbsightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }
 
        List<Superpower> dbsuperpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : dbsuperpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
        
        
        LocalDate date = LocalDate.parse("2020-01-08"); 
        LocalTime time = LocalTime.parse("12:32:22", 
            DateTimeFormatter.ISO_TIME);

        
        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location.setLatitude("TestLat");
        location.setLongitude("TestLong");
        location = locationDao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
//        organization.setSuperheros(superheroDao.getAllSuperheros());
        organization = organizationDao.addOrganization(organization);
        List<Organization> orgs = organizationDao.getAllOrganizations();
        
        Superpower superpower = new Superpower();
        superpower.setName("fast");
        superpower = superpowerDao.addSuperpower(superpower);
        Superpower superpower2 = new Superpower();
        superpower2.setName("fly");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(superpowers);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);
        superhero.setSightings(sightings);
        
        assertEquals(2, superpowers.size());
        assertTrue(superpowers.contains(superpower));
        assertTrue(superpowers.contains(superpower2));
    }

    
    /**
     * Test of updateSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testUpdateSuperpower() {
        List<Location> dblocations = locationDao.getAllLocations();
        for(Location location : dblocations) {
            locationDao.deleteLocationById(location.getId());
        }
        List<Organization> dborganizations = organizationDao.getAllOrganizations();
        for(Organization organization : dborganizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }
        
        List<Superhero> dbsuperheros = superheroDao.getAllSuperheros();
        for(Superhero superhero : dbsuperheros) {
            superheroDao.deleteSuperheroById(superhero.getId());
        }
       
        List<Sighting> dbsightings = sightingDao.getAllSightings();
        for(Sighting sighting : dbsightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }
 
        List<Superpower> dbsuperpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : dbsuperpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
        
        
        LocalDate date = LocalDate.parse("2020-01-08"); 
        LocalTime time = LocalTime.parse("12:32:22", 
            DateTimeFormatter.ISO_TIME);

        
        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location.setLatitude("TestLat");
        location.setLongitude("TestLong");
        location = locationDao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
//        organization.setSuperheros(superheroDao.getAllSuperheros());
        organization = organizationDao.addOrganization(organization);
        List<Organization> orgs = organizationDao.getAllOrganizations();
        
        Superpower superpower = new Superpower();
        superpower.setName("fast");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(superpowers);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);
        superhero.setSightings(sightings);
        
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getId());
        
        assertEquals(superpower, fromDao);
        
        superpower.setName("strong");
        superpowerDao.updateSuperpower(superpower);
        
        assertNotEquals(superpower, fromDao);
        
        fromDao = superpowerDao.getSuperpowerById(superpower.getId());
        
        
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of deleteSuperpowerById method, of class SuperpowerDaoDB.
     */
    @Test
    public void testDeleteSuperpowerById() {
        List<Location> dblocations = locationDao.getAllLocations();
        for(Location location : dblocations) {
            locationDao.deleteLocationById(location.getId());
        }
        List<Organization> dborganizations = organizationDao.getAllOrganizations();
        for(Organization organization : dborganizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }
        
        List<Superhero> dbsuperheros = superheroDao.getAllSuperheros();
        for(Superhero superhero : dbsuperheros) {
            superheroDao.deleteSuperheroById(superhero.getId());
        }
       
        List<Sighting> dbsightings = sightingDao.getAllSightings();
        for(Sighting sighting : dbsightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }
 
        List<Superpower> dbsuperpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : dbsuperpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
        
        
        LocalDate date = LocalDate.parse("2020-01-08"); 
        LocalTime time = LocalTime.parse("12:32:22", 
            DateTimeFormatter.ISO_TIME);

        
        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location.setLatitude("TestLat");
        location.setLongitude("TestLong");
        location = locationDao.addLocation(location);
        
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
//        organization.setSuperheros(superheroDao.getAllSuperheros());
        organization = organizationDao.addOrganization(organization);
        List<Organization> orgs = organizationDao.getAllOrganizations();
        
        Superpower superpower = new Superpower();
        superpower.setName("fast");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(superpowers);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);
        superhero.setSightings(sightings);
        
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getId());
         
        assertEquals(superpower, fromDao);
        
        superpowerDao.deleteSuperpowerById(superpower.getId());
        
        fromDao = superpowerDao.getSuperpowerById(superpower.getId());
        assertNull(fromDao);
        
    }
    
}
