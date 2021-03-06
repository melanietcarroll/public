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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * created 1/30/21
 *
 * @author Melanie Carroll
 */
@Controller
public class IndexController {

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

    @GetMapping("/")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Superhero> superheroes = superheroDao.getAllSuperheros();
        List<Location> locations = locationDao.getAllLocations();
        associateLocationAndSuperhero(sightings);

        model.addAttribute("superheroes", superheroes);
        model.addAttribute("locations", locations);

        sightings.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        List<Sighting> latestEntries = sightings.subList(Math.max(sightings.size() - 10, 0), sightings.size());

        List<Location> latestLocations = new ArrayList();
        for (Sighting sighting : latestEntries) {
            latestLocations.add(sighting.getLocation());
        }
        Set<Location> locationSet = new LinkedHashSet<>(latestLocations);
        latestLocations.clear();
        latestLocations.addAll(locationSet);
        Map<Float,Float> latLong = new HashMap();
        for (Location location : latestLocations){
            latLong.put(location.getLatitude(), location.getLongitude());
        }

        model.addAttribute("latLong", latLong);
        model.addAttribute("latestEntries", latestEntries);

        return "index";
    }

    private void associateLocationAndSuperhero(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setLocation(sightingDao.getLocationForSighting(sighting.getId()));
            sighting.setSuperhero(sightingDao.getSuperheroForSighting(sighting.getId()));
        }
    }
}
