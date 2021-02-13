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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

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

    Set<ConstraintViolation<Superhero>> violations = new HashSet<>();

    @GetMapping("superheroes")
    public String displaySuperheroes(Model model, Superhero superhero, BindingResult bindingResult, HttpServletRequest request) {
        List<Superhero> superheroes = superheroDao.getAllSuperheros();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
//        model.addAttribute("errors", violations);
        return "superheroes";
    }

    @PostMapping("addSuperhero")
    public String addSuperhero(@Valid Superhero superhero, BindingResult bindingResult, HttpServletRequest request, @RequestParam("imageFile") MultipartFile multipartFile, Model model) throws IOException {

        String[] superpowerIds = request.getParameterValues("superpowerId");
        String[] organizationIds = request.getParameterValues("organizationId");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        superhero.setPhoto(fileName);

        if (superpowerIds != null && superpowerIds.length > 0) {
            List<Superpower> superpowers = new ArrayList<>();
            for (String superpowerId : superpowerIds) {
                superpowers.add(superpowerDao.getSuperpowerById(Integer.parseInt(superpowerId)));
            }
            superhero.setSuperpowers(superpowers);
        } else {
            FieldError error = new FieldError("superhero", "superpowers", "Must include one superpower");
            bindingResult.addError(error);
        }

        if (organizationIds != null && organizationIds.length > 0) {
            List<Organization> organizations = new ArrayList<>();
            for (String organizationId : organizationIds) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
            }
            superhero.setOrganizations(organizations);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("superpowers", superpowerDao.getAllSuperpowers());
            model.addAttribute("organizations", organizationDao.getAllOrganizations());
            model.addAttribute("superhero", superhero);
            return "superheroes";
        }
        superheroDao.addSuperhero(superhero);
        if (!multipartFile.isEmpty()) {
            String uploadDir = "photos/" + superhero.getId();
            FileUploadUtility.saveImageFile(uploadDir, fileName, multipartFile);
        }
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
//        model.addAttribute("errors", violations);
        return "editSuperhero";
    }

    @PostMapping("editSuperhero")
    public String performEditSuperhero(@Valid Superhero superhero, BindingResult bindingResult, HttpServletRequest request, Model model) throws IOException {
        String[] superpowerIds = request.getParameterValues("superpowerId");
        String[] organizationIds = request.getParameterValues("organizationId");

        if (organizationIds != null && organizationIds.length > 0) {
            List<Organization> organizations = new ArrayList<>();
            for (String organizationId : organizationIds) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationId)));
            }
            superhero.setOrganizations(organizations);
        }

        List<Superpower> superpowers = new ArrayList<>();
        if (superpowerIds != null && superpowerIds.length > 0) {

            for (String superpowerId : superpowerIds) {
                superpowers.add(superpowerDao.getSuperpowerById(Integer.parseInt(superpowerId)));
            }
            superhero.setSuperpowers(superpowers);
        } else {
            FieldError error = new FieldError("superhero", "superpowers", "Must include one superpower");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("superpowers", superpowerDao.getAllSuperpowers());
            model.addAttribute("organizations", organizationDao.getAllOrganizations());
            model.addAttribute("superhero", superhero);
            return "editSuperhero";
        }
//        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
//        violations = validate.validate(superhero);
//        if (violations.isEmpty()) {
//            superheroDao.updateSuperhero(superhero);
//            return "redirect:/superheroes";
//        }
        superheroDao.updateSuperhero(superhero);
        return "redirect:/superheroes";

    }

}
