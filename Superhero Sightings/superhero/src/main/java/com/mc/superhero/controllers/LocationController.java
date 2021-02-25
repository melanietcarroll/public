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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * created 1/31/21
 *
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

    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    String message = null;

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        List<Superhero> superheroes = superheroDao.getAllSuperheros();
        List<Sighting> sightings = sightingDao.getAllSightings();
        violations.clear();
        
        model.addAttribute("locations", locations);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("sightings", sightings);
        model.addAttribute("errors", violations);
        model.addAttribute("message", message);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request, Model model) {
        message = null;
        Location location = new Location();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        location.setName(name);
        location.setAddress(address);
        location.setDescription(description);
        if (!latitude.matches("^(?:(?:\\-{1})?\\d+(?:\\.{1}\\d+)?)$") || !longitude.matches("^(?:(?:\\-{1})?\\d+(?:\\.{1}\\d+)?)$")) {
            message = "Latitude and Longitude must be numeric and in decimal format";
            model.addAttribute("message", message);
            model.addAttribute("errors", violations);
            message = null;
            return "locations";
        }
        try {
            location.setLongitude(Float.parseFloat(longitude));
            location.setLatitude(Float.parseFloat(latitude));
        } catch (NumberFormatException e) {
            message = "Latitude and Longitude must be numeric and in decimal format";
        }
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if (violations.isEmpty()) {
            locationDao.addLocation(location);
            return "redirect:/locations";
        }
        model.addAttribute("message", message);
        model.addAttribute("location", location);
        model.addAttribute("errors", violations);
        message = null;
        return "locations";
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
        model.addAttribute("errors", violations);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request, Model model, Integer id) {
        message = null;
        Location location = new Location();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        location.setName(name);
        location.setAddress(address);
        location.setDescription(description);
        location.setId(id);

        if (!latitude.matches("^(?:(?:\\-{1})?\\d+(?:\\.{1}\\d+)?)$") || !longitude.matches("^(?:(?:\\-{1})?\\d+(?:\\.{1}\\d+)?)$")) {
            message = "Latitude and Longitude must be numeric and in decimal format";
            model.addAttribute("message", message);
            location = locationDao.getLocationById(id);
            model.addAttribute("location", location);
            model.addAttribute("message", message);
            model.addAttribute("errors", violations);
            message = null;
            return "editLocation";
        }
        try {
            float parsedLongitude = Float.parseFloat(longitude);
            location.setLongitude(parsedLongitude);
            float parsedLatitude = Float.parseFloat(latitude);
            location.setLatitude(parsedLatitude);
        } catch (NumberFormatException e) {
            message = "Latitude and Longitude must be numeric and in decimal format";
        }
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if (violations.isEmpty()) {
            locationDao.updateLocation(location);
            return "redirect:/locations";
        }
        model.addAttribute("message", message);
        model.addAttribute("location", location);
        model.addAttribute("errors", violations);
        message = null;
        return "editLocation";
    }

}
