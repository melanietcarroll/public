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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * created 2/1/21
 *
 * @author Melanie Carroll
 */
@Controller
public class SightingController {

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

    @GetMapping("sightings")
    public String displaySightings(Model model, Sighting sighting, BindingResult bindingResult, HttpServletRequest request) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Superhero> superheroes = superheroDao.getAllSuperheros();
        List<Location> locations = locationDao.getAllLocations();
        associateLocationAndSuperhero(sightings);
        model.addAttribute("sightings", sightings);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("locations", locations);
        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(@Valid Sighting sighting, BindingResult bindingResult, HttpServletRequest request, Model model) {
        String superheroId = request.getParameter("superheroId");
        String locationId = request.getParameter("locationId");

        if (superheroId != null) {
            sighting.setSuperhero(superheroDao.getSuperheroById(Integer.parseInt(superheroId)));

        } else {
            FieldError error = new FieldError("sighting", "superhero", "Must include one superhero");
            bindingResult.addError(error);
        }
        if (locationId != null) {
            sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));
        } else {
            FieldError error = new FieldError("sighting", "location", "Must include one location");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("superheroes", superheroDao.getAllSuperheros());
            model.addAttribute("locations", locationDao.getAllLocations());
            model.addAttribute("sighting", sighting);
            return "sightings";
        }

        sightingDao.addSighting(sighting);

        return "redirect:/sightings";
    }

    @GetMapping("sightingDetail")
    public String sightingDetail(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "sightingDetail";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        List<Superhero> superheroes = superheroDao.getAllSuperheros();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("sighting", sighting);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("locations", locations);
        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(@Valid Sighting sighting, BindingResult bindingResult, HttpServletRequest request, Model model) {
        String superheroId = request.getParameter("superheroId");
        String locationId = request.getParameter("locationId");

        if (superheroId != null) {
            sighting.setSuperhero(superheroDao.getSuperheroById(Integer.parseInt(superheroId)));

        } else {
            FieldError error = new FieldError("sighting", "superhero", "Must include one superhero");
            bindingResult.addError(error);
        }

        if (locationId != null) {
            sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));
        } else {
            FieldError error = new FieldError("sighting", "location", "Must include one location");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("superheroes", superheroDao.getAllSuperheros());
            model.addAttribute("locations", locationDao.getAllLocations());
            model.addAttribute("sighting", sighting);
            return "editSighting";
        }

        sightingDao.updateSighting(sighting);

        return "redirect:/sightings";
    }

    @GetMapping("searchSighting")
    public String searchSightingByDate(@Valid Sighting sighting, BindingResult bindingResult, String searchDate, HttpServletRequest request, Model model) {
        if (searchDate.isBlank() || searchDate.isEmpty()) {
            FieldError error = new FieldError("sighting", "date", "Must select a date");
            bindingResult.addError(error);
            return "sightings";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(searchDate, formatter);
        List<Sighting> sightings = sightingDao.getSightingsForSuperheroAndLocationByDate(localDate);
        associateLocationAndSuperhero(sightings);
        model.addAttribute("sightings", sightings);
        return "sightingsByDate";
    }

    private void associateLocationAndSuperhero(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setLocation(sightingDao.getLocationForSighting(sighting.getId()));
            sighting.setSuperhero(sightingDao.getSuperheroForSighting(sighting.getId()));
        }
    }

}
