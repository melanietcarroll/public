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
import com.mc.superhero.entities.Organization;
import com.mc.superhero.entities.Superhero;
import com.mc.superhero.entities.Superpower;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * created 1/31/21
 *
 * @author Melanie Carroll
 */
@Controller
public class SuperheroController {

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

    @GetMapping("superheroes")
    public String displaySuperheroes(Model model) {
        List<Superhero> superheroes = superheroDao.getAllSuperheros();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
        return "superheroes";
    }

    @PostMapping("addSuperhero")
    public String addSuperhero(Superhero superhero, HttpServletRequest request) {
        String[] superpowerIds = request.getParameterValues("superpowerId");
        String[] organizationIds = request.getParameterValues("organizationId");

        if (superpowerIds != null && superpowerIds.length > 0) {
            List<Superpower> superpowers = new ArrayList<>();
            for (String superpowerId : superpowerIds) {
                superpowers.add(superpowerDao.getSuperpowerById(Integer.parseInt(superpowerId)));
            }
            superhero.setSuperpowers(superpowers);
        }
        if (organizationIds != null && organizationIds.length > 0) {
            List<Organization> organizations = new ArrayList<>();
            for (String organizationId : organizationIds) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
            }
            superhero.setOrganizations(organizations);
        }
        superheroDao.addSuperhero(superhero);

        return "redirect:/superheroes";
    }

    @GetMapping("superheroDetail")
    public String superheroDetail(Integer id, Model model) {
        Superhero superhero = superheroDao.getSuperheroById(id);
        model.addAttribute("superhero", superhero);
        return "superheroDetail";
    }

    @GetMapping("deleteSuperhero")
    public String deleteSuperhero(Integer id) {
        superheroDao.deleteSuperheroById(id);
        return "redirect:/superheroes";
    }

    @GetMapping("editSuperhero")
    public String editSuperhero(Integer id, Model model) {
        Superhero superhero = superheroDao.getSuperheroById(id);
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("superhero", superhero);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
        return "editSuperhero";
    }

    @PostMapping("editSuperhero")
    public String performEditSuperhero(Superhero superhero, HttpServletRequest request) {
        String[] superpowerIds = request.getParameterValues("superpowerId");
        String[] organizationIds = request.getParameterValues("organizationId");

        if (superpowerIds != null && superpowerIds.length > 0) {
            List<Superpower> superpowers = new ArrayList<>();
            for (String superpowerId : superpowerIds) {
                superpowers.add(superpowerDao.getSuperpowerById(Integer.parseInt(superpowerId)));
            }
            superhero.setSuperpowers(superpowers);
        }

        if (organizationIds != null && organizationIds.length > 0) {
            List<Organization> organizations = new ArrayList<>();
            for (String organizationId : organizationIds) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
            }
            superhero.setOrganizations(organizations);
        }

        superheroDao.updateSuperhero(superhero);
        return "redirect:/superheroes";
    }
}
