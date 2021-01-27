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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.Test;
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
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getId());
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
        
        Sighting fromDao = sightingDao.getSightingById(sightingId);
        fromDao.setSuperhero(superhero);
        fromDao.setLocation(location);
        
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of getAllSightings method, of class SightingDaoDB.
     */
    @Test
    public void testGetAllSightings() {
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
        List<Superpower> superpowersList = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(superpowersList);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(date);
        sighting2.setTime(time);
        sighting2.setLocation(location);
        sighting2.setSuperhero(superhero);
        sighting2 = sightingDao.addSighting(sighting2);
        int sightingId2 = sighting2.getId();
        
         List<Sighting> testSightings = sightingDao.getAllSightings();
        for (Sighting testSighting : testSightings){
            testSighting.setLocation(location);
            testSighting.setSuperhero(superhero);
            
        }
        
        assertEquals(2, testSightings.size());
        assertTrue(testSightings.contains(sighting));
        assertTrue(testSightings.contains(sighting2));
        
    }

    /**
     * Test of updateSighting method, of class SightingDaoDB.
     */
    @Test
    public void testUpdateSighting() {
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
        LocalDate date2 = LocalDate.parse("2020-10-23"); 
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
        
        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        fromDao.setSuperhero(superhero);
        fromDao.setLocation(location);
        
        assertEquals(sighting, fromDao);
        
        
        sighting.setDate(date2);
        sightingDao.updateSighting(sighting);
        
        assertNotEquals(sighting, fromDao);
        
        fromDao = sightingDao.getSightingById(sighting.getId());
        fromDao.setSuperhero(superhero);
        fromDao.setLocation(location);
       
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of deleteSightingById method, of class SightingDaoDB.
     */
    @Test
    public void testDeleteSightingById() {
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
        
        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
         sightingDao.deleteSightingById(sighting.getId());
        
        fromDao = sightingDao.getSightingById(sighting.getId());
        assertNull(fromDao);
    }

    /**
     * Test of getLocationForSighting method, of class SightingDaoDB.
     */
    @Test
    public void testGetLocationForSighting() {
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
        
        Location locationsForSighting = sightingDao.getLocationForSighting(sightingId);
        assertEquals(locationsForSighting, location);
    }

    /**
     * Test of getSuperheroForSighting method, of class SightingDaoDB.
     */
    @Test
    public void testGetSuperheroForSighting() {
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
        List<Sighting> sightingsList = new ArrayList();
        sightingsList.add(sighting);
        superhero.setSightings(sightingsList);
        
        Superhero superheroForSighting = sightingDao.getSuperheroForSighting(sightingId);
        superheroForSighting.setOrganizations(orgs);
        superheroForSighting.setSightings(sightingsList);
        superheroForSighting.setSuperpowers(superpowers);
        assertEquals(superheroForSighting, superhero);
    }

    /**
     * Test of getSightingsForSuperheroAndLocationByDate method, of class SightingDaoDB.
     */
    @Test
    public void testGetSightingsForSuperheroAndLocationByDate() {
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
        List<Superpower> superpowersList = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(superpowersList);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        List<Sighting> sightingsList = new ArrayList();
        sightingsList.add(sighting);
        superhero.setSightings(sightingsList);
        
        List<Sighting> sightingByDate = sightingDao.getSightingsForSuperheroAndLocationByDate(date);
        assertEquals(1, sightingByDate.size());
    }
    
}
