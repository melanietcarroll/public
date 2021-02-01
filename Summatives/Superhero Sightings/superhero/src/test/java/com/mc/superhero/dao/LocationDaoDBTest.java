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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Melanie Carroll
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LocationDaoDBTest {

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

    public LocationDaoDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationById(location.getId());
        }
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getId());
        }

        List<Superhero> superheros = superheroDao.getAllSuperheros();
        for (Superhero superhero : superheros) {
            superheroDao.deleteSuperheroById(superhero.getId());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }

        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getLocationById method, of class LocationDaoDB.
     */
    @Test
    public void testAddAndGetLocation() {
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
        superhero2.setName("Test");
        superhero2.setDescription("very fast");
        superhero2.setSuperpowers(listSuperpowers);
        superhero2.setOrganizations(orgs);
        superhero2 = superheroDao.addSuperhero(superhero2);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);

        List<Superhero> testSuperheros = new ArrayList();
        testSuperheros.add(superhero);
        testSuperheros.add(superhero2);

        Location fromDao = locationDao.getLocationById(location.getId());
        fromDao.setSuperheroes(testSuperheros);

        assertEquals(location, fromDao);
    }

    /**
     * Test of getAllLocations method, of class LocationDaoDB.
     */
    @Test
    public void testGetAllLocations() {
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

        Location location2 = new Location();
        location2.setName("Test Name2");
        location2.setDescription("Test Description2");
        location2.setAddress("Test Address2");
        location2.setLatitude("TestLat");
        location2.setLongitude("TestLong");
        location2 = locationDao.addLocation(location2);
        
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");

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
        superhero2.setName("Test");
        superhero2.setDescription("very fast");
        superhero2.setSuperpowers(listSuperpowers);
        superhero2.setOrganizations(orgs);
        superhero2 = superheroDao.addSuperhero(superhero2);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);

        List<Superhero> testSuperheros = new ArrayList();
        testSuperheros.add(superhero);
        testSuperheros.add(superhero2);

        List<Location> locations = locationDao.getAllLocations();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }
//
//    /**
//     * Test of updateLocation method, of class LocationDaoDB.
//     */
    @Test
    public void testUpdateLocation() {
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
        superhero2.setName("Test");
        superhero2.setDescription("very fast");
        superhero2.setSuperpowers(listSuperpowers);
        superhero2.setOrganizations(orgs);
        superhero2 = superheroDao.addSuperhero(superhero2);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setTime(time);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);

        List<Superhero> testSuperheros = new ArrayList();
        testSuperheros.add(superhero);
        testSuperheros.add(superhero2);

        Location fromDao = locationDao.getLocationById(location.getId());
        fromDao.setSuperheroes(testSuperheros);
        assertEquals(location, fromDao);

        location.setName("New Test First");
        locationDao.updateLocation(location);

        assertNotEquals(location, fromDao);

        fromDao = locationDao.getLocationById(location.getId());
        fromDao.setSuperheroes(testSuperheros);

        assertEquals(location, fromDao);
    }
//
//    /**
//     * Test of deleteLocationById method, of class LocationDaoDB.
//     */
    @Test
    public void testDeleteLocationById() {
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

        Superpower superpower = new Superpower();
        superpower.setName("fast");
        superpower = superpowerDao.addSuperpower(superpower);
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();

        Superhero superhero = new Superhero();
        superhero.setName("Test");
        superhero.setDescription("very fast");
        superhero.setSuperpowers(superpowers);
        superhero = superheroDao.addSuperhero(superhero);
        
        List<Superhero> supList = new ArrayList();
        supList.add(superhero);

        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setLocation(location);
        sighting.setSuperhero(superhero);
        sighting = sightingDao.addSighting(sighting);

        Location fromDao = locationDao.getLocationById(location.getId());
        fromDao.setSuperheroes(supList);
        assertEquals(location, fromDao);

        locationDao.deleteLocationById(location.getId());

        fromDao = locationDao.getLocationById(location.getId());
        assertNull(fromDao);
    }
@Test
    public void testGetSuperherosForLocation() {
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

        List<Sighting> listSightings = new ArrayList();
        listSightings.add(sighting);
        superhero.setSightings(listSightings);

        List<Superhero> getSuperherosForLocation = locationDao.getSuperherosForLocation(location.getId());
        assertEquals(1, getSuperherosForLocation.size());
}
}
