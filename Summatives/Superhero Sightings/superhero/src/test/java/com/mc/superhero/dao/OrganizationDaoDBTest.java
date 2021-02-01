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
     * Test of getOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddAndGetOrganization() {
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

        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
        organization.setSuperheroes(superheros);
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        fromDao.setSuperheroes(superheros);

        assertEquals(organization, fromDao);
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
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

        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
        organization.setSuperheroes(superheros);
        organization = organizationDao.addOrganization(organization);

        Organization organization2 = new Organization();
        organization2.setName("Test Name");
        organization2.setDescription("Test Description");
        organization2.setAddress("Test Address");
        organization2.setSuperheroes(superheros);
        organization2 = organizationDao.addOrganization(organization2);

        List<Organization> testOrganizations = organizationDao.getAllOrganizations();

        assertEquals(2, testOrganizations.size());

    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
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

        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
        organization.setSuperheroes(superheros);
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        fromDao.setSuperheroes(superheros);
        assertEquals(organization, fromDao);

        organization.setName("New Test First");
        organizationDao.updateOrganization(organization);

        assertNotEquals(location, fromDao);

        fromDao = organizationDao.getOrganizationById(organization.getId());
        fromDao.setSuperheroes(superheros);

        assertEquals(organization, fromDao);
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
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

        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescription("Test Description");
        organization.setAddress("Test Address");
        organization.setSuperheroes(superheros);
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        fromDao.setSuperheroes(superheros);
        assertEquals(organization, fromDao);

        organizationDao.deleteOrganizationById(organization.getId());

        fromDao = organizationDao.getOrganizationById(organization.getId());
        assertNull(fromDao);
    }

}
