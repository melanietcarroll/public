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
import java.util.ArrayList;
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
public class OrganizationController {

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

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationDao.getAllOrganizations();
        List<Superhero> superheroes = superheroDao.getAllSuperheros();

        model.addAttribute("organizations", organizations);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("errors", violations);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(Organization organization, HttpServletRequest request) {
        String[] superheroIds = request.getParameterValues("superheroId");

        List<Superhero> superheroes = new ArrayList<>();
        for (String superheroId : superheroIds) {
            superheroes.add(superheroDao.getSuperheroById(Integer.parseInt(superheroId)));
        }
        organization.setSuperheroes(superheroes);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if (violations.isEmpty()) {
            organizationDao.addOrganization(organization);
        }
//        organization.setSuperheroes(superheroes);
//        organizationDao.addOrganization(organization);

        return "redirect:/organizations";
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(Integer id, Model model) {
        Organization organization = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "organizationDetail";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {
        organizationDao.deleteOrganizationById(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model) {
        Organization organization = organizationDao.getOrganizationById(id);
        List<Superhero> superheroes = superheroDao.getAllSuperheros();

        model.addAttribute("organization", organization);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("errors", violations);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(Organization organization, HttpServletRequest request) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);
        String[] superheroIds = request.getParameterValues("superheroId");

        if (violations.isEmpty() && (superheroIds != null)) {
        
        List<Superhero> superheroes = new ArrayList<>();
        for (String superheroId : superheroIds) {
            superheroes.add(superheroDao.getSuperheroById(Integer.parseInt(superheroId)));
        }
        organization.setSuperheroes(superheroes);
        organizationDao.updateOrganization(organization);
        }

        return "redirect:/organizations";
    }
}
