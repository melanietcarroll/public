/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.dao;

import com.mc.superhero.dao.LocationDaoDB.LocationMapper;
import com.mc.superhero.dao.OrganizationDaoDB.OrganizationMapper;
import com.mc.superhero.dao.SightingDaoDB.SightingMapper;
import com.mc.superhero.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.mc.superhero.entities.Location;
import com.mc.superhero.entities.Organization;
import com.mc.superhero.entities.Sighting;
import com.mc.superhero.entities.Superhero;
import com.mc.superhero.entities.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * created 1/25/21
 *
 * @author Melanie Carroll
 */
@Repository
public class SuperheroDaoDB implements SuperheroDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superhero getSuperheroById(int id) {
        try {
            final String SELECT_SUPERHERO_BY_ID = "SELECT * FROM Superhero WHERE id = ?";
            Superhero superhero = jdbc.queryForObject(SELECT_SUPERHERO_BY_ID, new SuperheroMapper(), id);
            superhero.setSuperpowers(getSuperpowersForSuperhero(id));
            superhero.setSightings(getSightingsForSuperhero(id));
            superhero.setOrganizations(getOrganizationsForSuperhero(id));
            
            return superhero;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    public List<Superpower> getSuperpowersForSuperhero(int id) {
        final String SELECT_SUPERPOWERS_FOR_SUPERHERO = "SELECT pow.* FROM Superpower pow "
                + "JOIN Superhero_Superpower ss ON ss.superpowerId = pow.id WHERE ss.superheroId = ?";
        return jdbc.query(SELECT_SUPERPOWERS_FOR_SUPERHERO, new SuperpowerMapper(), id);
    }

    public List<Sighting> getSightingsForSuperhero(int id) {
        final String SELECT_SIGHTING_FOR_SUPERHERO = "SELECT s.* FROM Sighting s "
                + "JOIN Superhero sup ON s.superheroId = sup.id WHERE sup.id = ?";
        return jdbc.query(SELECT_SIGHTING_FOR_SUPERHERO, new SightingMapper(), id);
    }

    public List<Organization> getOrganizationsForSuperhero(int id) {
        final String SELECT_ORGANIZATIONS_FOR_SUPERHERO = "SELECT org.* FROM Organization org "
                + "JOIN Superhero_Organization so ON so.organizationId = org.id WHERE so.superheroId = ?";
        return jdbc.query(SELECT_ORGANIZATIONS_FOR_SUPERHERO, new OrganizationMapper(), id);
    }

    @Override
    public List<Superhero> getAllSuperheros() {
       final String SELECT_ALL_SUPERHEROS = "SELECT * FROM Superhero";
        List<Superhero> superheros = jdbc.query(SELECT_ALL_SUPERHEROS, new SuperheroMapper());
        associateOrganizationsAndSuperpowersAndSightings(superheros);
        
        return superheros;
    }

    private void associateOrganizationsAndSuperpowersAndSightings(List<Superhero> superheros) {
        for (Superhero superhero : superheros) {
            superhero.setOrganizations(getOrganizationsForSuperhero(superhero.getId()));
            superhero.setSightings(getSightingsForSuperhero(superhero.getId()));
            superhero.setSuperpowers(getSuperpowersForSuperhero(superhero.getId()));
        }
    }

    @Override
    @Transactional
    public Superhero addSuperhero(Superhero superhero) {
       final String INSERT_SUPERHERO = "INSERT INTO Superhero(name, description) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SUPERHERO,
                superhero.getName(),
                superhero.getDescription());
               
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superhero.setId(newId);
        insertSuperheroSuperpower(superhero);
        if (superhero.getOrganizations() != null){
            
        insertSuperheroOrganization(superhero);
                }
        
        return superhero;
    }

    public void insertSuperheroOrganization(Superhero superhero) {
        final String INSERT_SUPERHERO_ORGANIZATION = "INSERT INTO "
                + "Superhero_Organization(superheroId, organizationId) VALUES(?,?)";
        for(Organization organization : superhero.getOrganizations()) {
            jdbc.update(INSERT_SUPERHERO_ORGANIZATION, 
                    superhero.getId(),
                    organization.getId());
        }
    }
    
    private void insertSuperheroSuperpower(Superhero superhero) {
        final String INSERT_SUPERHERO_SUPERPOWER = "INSERT INTO "
                + "Superhero_Superpower(superheroId, superpowerId) VALUES(?,?)";
        for(Superpower superpower : superhero.getSuperpowers()) {
            jdbc.update(INSERT_SUPERHERO_SUPERPOWER, 
                    superhero.getId(),
                    superpower.getId());
        }
    }

    @Override
    @Transactional
    public void updateSuperhero(Superhero superhero) {
         final String UPDATE_SUPERHERO = "UPDATE Superhero SET name = ?, description = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_SUPERHERO, 
                superhero.getName(), 
                superhero.getDescription(),
                superhero.getId());
        
        final String DELETE_SUPERHERO_SUPERPOWER = "DELETE FROM Superhero_Superpower WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_SUPERPOWER, superhero.getId());
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE FROM Superhero_Organization WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, superhero.getId());
        insertSuperheroSuperpower(superhero);
        if (superhero.getOrganizations() != null){    
        insertSuperheroOrganization(superhero);
                }
    }

    @Override
    @Transactional
    public void deleteSuperheroById(int id) {
          final String DELETE_SUPERHERO_SUPERPOWER = "DELETE FROM Superhero_Superpower WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_SUPERPOWER, id);
        
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE FROM Superhero_Organization WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, id);
        
        final String DELETE_SUPERHERO_SIGHTING = "DELETE FROM Sighting "
                + "WHERE superheroId = ?";
        jdbc.update(DELETE_SUPERHERO_SIGHTING, id);
        
        final String DELETE_SUPERHERO = "DELETE FROM Superhero WHERE id = ?";
        jdbc.update(DELETE_SUPERHERO, id);
    }

    @Override
    public List<Location> getLocationsForSuperhero(int id) {
        final String SELECT_LOCATIONS_FOR_SUPERHERO = "SELECT l.* FROM Location l "
                + "JOIN Sighting s ON s.locationId = l.id "
                + "JOIN Superhero ON s.superheroId = Superhero.id "
                + "WHERE Superhero.id = ?";
        return jdbc.query(SELECT_LOCATIONS_FOR_SUPERHERO, new LocationMapper(), id);
    }

    @Override
    public List<Superhero> getSuperherosForLocation(int id) {
        final String SELECT_LOCATIONS_FOR_SUPERHERO = "SELECT sup.* FROM Superhero sup "
                + "JOIN Sighting s ON s.superheroId = sup.id "
                + "JOIN Location ON s.locationId = Location.id"
                + "WHERE Location.id = ?";
               
        return jdbc.query(SELECT_LOCATIONS_FOR_SUPERHERO, new SuperheroMapper(), id);
    }

    public static final class SuperheroMapper implements RowMapper<Superhero> {

        @Override
        public Superhero mapRow(ResultSet rs, int index) throws SQLException {
            Superhero superhero = new Superhero();
            superhero.setId(rs.getInt("id"));
            superhero.setName(rs.getString("name"));
            superhero.setDescription(rs.getString("description"));

            return superhero;
        }
    }
}
