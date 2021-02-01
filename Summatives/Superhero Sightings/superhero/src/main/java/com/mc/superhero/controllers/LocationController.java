/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.controllers;

import com.mc.superhero.dao.LocationDao;
import com.mc.superhero.dao.OrganizationDao;
import com.mc.superhero.dao.SightingDao;
import com.mc.superhero.dao.SuperheroDao;
import com.mc.superhero.dao.SuperpowerDao;
import com.mc.superhero.entities.Location;
import com.mc.superhero.entities.Sighting;
import com.mc.superhero.entities.Superhero;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * created 1/31/21
 * @author Melanie Carroll
 */
@Controller
public class LocationController {
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
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        List<Superhero> superheroes = superheroDao.getAllSuperheros();
        List<Sighting> sightings = sightingDao.getAllSightings();
        model.addAttribute("locations", locations);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("sightings", sightings);
        return "locations";
    }
    @PostMapping("addLocation")
    public String addLocation(Location location, HttpServletRequest request) {
        locationDao.addLocation(location);
        return "redirect:/locations";
    }
     @GetMapping("locationDetail")
    public String locationDetail(Integer id, Model model) {
        Location location = locationDao.getLocationById(id);
        model.addAttribute("location", location);
        return "locationDetail";
    }
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id) {
        locationDao.deleteLocationById(id);
        return "redirect:/locations";
    }
    @GetMapping("editLocation")
    public String editLocation(Integer id, Model model) {
        Location location = locationDao.getLocationById(id);
        model.addAttribute("location", location);
        return "editLocation";
    }
     @PostMapping("editLocation")
    public String performEditLocation(Location location, HttpServletRequest request) {
        locationDao.updateLocation(location);
        
        return "redirect:/locations";
    }
    
}
