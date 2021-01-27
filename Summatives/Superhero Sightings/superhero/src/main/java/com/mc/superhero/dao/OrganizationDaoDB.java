/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.dao;

import com.mc.superhero.dao.SuperheroDaoDB.SuperheroMapper;
import com.mc.superhero.entities.Organization;
import com.mc.superhero.entities.Superhero;
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
 * @author Melanie Carroll
 */
@Repository
public class OrganizationDaoDB implements OrganizationDao {
    @Autowired
   JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationById(int id) {
       try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM Organization WHERE id = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
            organization.setSuperheros(getSuperherosForOrganization(id));
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM Organization";
        List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
        associateSuperheros(organizations);
        return organizations;
    }
    private void associateSuperheros(List<Organization> organizations) {
        for (Organization organization : organizations) {
            organization.setSuperheros(getSuperherosForOrganization(organization.getId()));
        }
    }

    @Override
    public Organization addOrganization(Organization organization) {
         final String INSERT_ORGANIZATION = "INSERT INTO Organization(name, description, address) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        return organization;
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE Organization SET name = ?, description = ?, address = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getId());
        
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE FROM Superhero_Organization WHERE organizationId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, organization.getId());
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_SUPERHERO_ORGANIZATION = "DELETE FROM Superhero_Organization WHERE organizationId = ?";
        jdbc.update(DELETE_SUPERHERO_ORGANIZATION, id);
        
        final String DELETE_ORGANIZATION = "DELETE FROM Organization WHERE id = ?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }

    @Override
    public List<Superhero> getSuperherosForOrganization(int id) {
        final String SELECT_SUPERHEROS_FOR_ORGANIZATION = "SELECT sup.* FROM Superhero sup "
                + "JOIN Superhero_Organization so ON so.superheroId = sup.id WHERE so.organizationId = ?";
        return jdbc.query(SELECT_SUPERHEROS_FOR_ORGANIZATION, new SuperheroMapper(), id);
    }
     public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            return organization;
        }
    }
}
