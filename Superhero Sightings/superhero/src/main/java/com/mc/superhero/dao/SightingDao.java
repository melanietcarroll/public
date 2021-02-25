/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.dao;

import com.mc.superhero.entities.Location;
import com.mc.superhero.entities.Sighting;
import com.mc.superhero.entities.Superhero;
import java.time.LocalDate;
import java.util.List;

/**
 * created 1/24/21
 * @author Melanie Carroll
 */
public interface SightingDao {
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);
    
    Location getLocationForSighting(int id);
    Superhero getSuperheroForSighting(int id);
    List<Sighting> getSightingsForSuperheroAndLocationByDate(LocalDate date);
}
