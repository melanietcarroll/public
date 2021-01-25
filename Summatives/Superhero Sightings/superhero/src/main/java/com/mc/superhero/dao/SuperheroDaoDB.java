/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.dao;

import com.mc.superhero.dao.OrganizationDaoDB.OrganizationMapper;
import com.mc.superhero.dao.SightingDaoDB.SightingMapper;
import com.mc.superhero.dao.SuperpowerDaoDB.SuperpowerMapper;
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

/**
 * created 1/25/21
 * @author Melanie Carroll
 */
@Repository
public class SuperheroDaoDB implements SuperheroDao{
    @Autowired
   JdbcTemplate jdbc;

    @Override
    public Superhero getSuperheroById(int id) {
        try {
            final String SELECT_SUPERHERO_BY_ID = "SELECT * FROM Superhero WHERE id = ?";
            Superhero superhero = jdbc.queryForObject(SELECT_SUPERHERO_BY_ID, new SuperheroMapper(), id);
            course.setTeacher(getTeacherForCourse(id));
            course.setStudents(getStudentsForCourse(id));
            return course;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    public List<Superpower> getSuperpowersForSuperhero(int id) {
        final String SELECT_SUPERPOWERS_FOR_SUPERHERO = "SELECT pow.* FROM Superpower pow "
                + "JOIN Superhero_Superpower ss ON ss.superpowerId = pow.id WHERE ss.superpowerId = ?";
        return jdbc.query(SELECT_SUPERPOWERS_FOR_SUPERHERO, new SuperpowerMapper(), id);
    }

    public List<Sighting> getSightingsForSuperhero(int id) {
         final String SELECT_SIGHTING_FOR_SUPERHERO = "SELECT s.* FROM Sighting s "
                + "JOIN Superhero sup ON s.superheroId = sup.id WHERE sup.id = ?";
        return jdbc.query(SELECT_SIGHTING_FOR_SUPERHERO, new SightingMapper(), id);
    }
    public List<Organization> getOrganizationsForSuperhero(int id){
        final String SELECT_ORGANIZATIONS_FOR_SUPERHERO = "SELECT org.* FROM Organization org "
                + "JOIN Superhero_Organization so ON so.organizationId = org.id WHERE so.superheroId = ?";
        return jdbc.query(SELECT_ORGANIZATIONS_FOR_SUPERHERO, new OrganizationMapper(), id);
    }

    @Override
    public List<Superhero> getAllSuperheros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Superhero addSuperhero(Superhero superhero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSuperhero(Superhero superhero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperheroById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
