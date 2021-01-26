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
import org.junit.jupiter.api.Test;
//import org.junit.Test;
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
public class SuperheroDaoDBTest {
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
    
    public SuperheroDaoDBTest() {
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
     * Test of getSuperheroById method, of class SuperheroDaoDB.
     */
    @Test
    public void testAddGetSuperheroById() {
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
        
        Superhero fromDao = superheroDao.getSuperheroById(superhero.getId());
        fromDao.setSuperpowers(superpowers);
        fromDao.setOrganizations(orgs);
        fromDao.setSightings(sightings);
        
        assertEquals(superhero, fromDao);
    }

    /**
     * Test of getSuperpowersForSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetSuperpowersForSuperhero() {
    }

    /**
     * Test of getSightingsForSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetSightingsForSuperhero() {
    }

    /**
     * Test of getOrganizationsForSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetOrganizationsForSuperhero() {
    }

    /**
     * Test of getAllSuperheros method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetAllSuperheros() {
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
        List<Superpower> listSuperpowers = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(listSuperpowers);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        Superhero superhero2 = new Superhero();
        superhero2.setName("Test2");
        superhero2.setDescription("very fast2");
        superhero2.setSuperpowers(listSuperpowers);
        superhero2.setOrganizations(orgs);
        superhero2 = superheroDao.addSuperhero(superhero2);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        List<Sighting> listSightings = new ArrayList();
        listSightings.add(sighting);
        superhero.setSightings(listSightings);
        superhero2.setSightings(listSightings);
        
        List<Superhero> listSuperheros = superheroDao.getAllSuperheros();
        for (Superhero superheroTest : listSuperheros){
            superheroTest.setSightings(listSightings);
            superheroTest.setOrganizations(orgs);
            superheroTest.setSuperpowers(listSuperpowers);
        }
        
        
        assertEquals(2, listSuperheros.size());
        assertTrue(listSuperheros.contains(superhero));
        assertTrue(listSuperheros.contains(superhero2));
        
    }

    
    /**
     * Test of insertSuperheroOrganization method, of class SuperheroDaoDB.
     */
    @Test
    public void testInsertSuperheroOrganization() {
    }

    /**
     * Test of updateSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testUpdateSuperhero() {
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
        List<Superpower> listSuperpowers = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(listSuperpowers);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        List<Sighting> listSightings = new ArrayList();
        listSightings.add(sighting);
        superhero.setSightings(listSightings);
        
        
        Superhero fromDao = superheroDao.getSuperheroById(superhero.getId());
        fromDao.setOrganizations(orgs);
        fromDao.setSightings(listSightings);
        fromDao.setSuperpowers(listSuperpowers);
        assertEquals(superhero, fromDao);
        
        superhero.setName("New Test First");
        superheroDao.updateSuperhero(superhero);
        
        assertNotEquals(superhero, fromDao);
        
        fromDao = superheroDao.getSuperheroById(superhero.getId());
        fromDao.setOrganizations(orgs);
        fromDao.setSightings(listSightings);
        fromDao.setSuperpowers(listSuperpowers);
        
        assertEquals(superhero, fromDao);
        
    }

    /**
     * Test of deleteSuperheroById method, of class SuperheroDaoDB.
     */
    @Test
    public void testDeleteSuperheroById() {
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
        List<Superpower> listSuperpowers = superpowerDao.getAllSuperpowers();
        
        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(listSuperpowers);
        superhero.setOrganizations(orgs);
        superhero = superheroDao.addSuperhero(superhero);
        
        
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);
        int sightingId = sighting.getId();
        List<Sighting> listSightings = new ArrayList();
        listSightings.add(sighting);
        superhero.setSightings(listSightings);
        
        
         Superhero fromDao = superheroDao.getSuperheroById(superhero.getId());
         fromDao.setOrganizations(orgs);
        fromDao.setSightings(listSightings);
        fromDao.setSuperpowers(listSuperpowers);
        assertEquals(superhero, fromDao);
        
        superheroDao.deleteSuperheroById(superhero.getId());
        
        fromDao = superheroDao.getSuperheroById(location.getId());
        assertNull(fromDao);
    }

    /**
     * Test of getLocationsForSuperhero method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetLocationsForSuperhero() {
    }

    /**
     * Test of getSuperherosForLocation method, of class SuperheroDaoDB.
     */
    @Test
    public void testGetSuperherosForLocation() {
    }
    
}
