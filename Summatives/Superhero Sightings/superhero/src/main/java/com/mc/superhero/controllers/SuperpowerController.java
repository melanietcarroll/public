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
import com.mc.superhero.entities.Superpower;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
public class SuperpowerController {

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

    Set<ConstraintViolation<Superpower>> violations = new HashSet<>();

    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("errors", violations);
        return "superpowers";
    }

    @PostMapping("addSuperpower")
    public String addSuperpower(Superpower superpower) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);

        if (violations.isEmpty()) {
            superpowerDao.addSuperpower(superpower);
        }
//        superpowerDao.addSuperpower(superpower);

        return "redirect:/superpowers";
    }

    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(Integer id) {
        superpowerDao.deleteSuperpowerById(id);
        return "redirect:/superpowers";
    }

    @GetMapping("editSuperpower")
    public String editSuperpower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superpower superpower = superpowerDao.getSuperpowerById(id);
        model.addAttribute("errors", violations);
        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }

    @PostMapping("editSuperpower")
    public String performEditSuperpower(Superpower superpower, HttpServletRequest request) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Superpower superpower = superpowerDao.getSuperpowerById(id);
//        superpower.setName(request.getParameter("name"));
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);

        if (violations.isEmpty()) {
            superpowerDao.updateSuperpower(superpower);
        }
//        superpowerDao.updateSuperpower(superpower);

        return "redirect:/superpowers";
    }

    @GetMapping("superpowerDetail")
    public String courseDetail(Integer id, Model model) {
        Superpower superpower = superpowerDao.getSuperpowerById(id);
        model.addAttribute("superpower", superpower);
        return "superpowerDetail";
    }
}
