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
    
    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
 }
     @PostMapping("addSuperpower")
    public String addSuperpower(HttpServletRequest request) {
        String name = request.getParameter("name");
        
        Superpower superpower = new Superpower();
        superpower.setName(name);
        
        superpowerDao.addSuperpower(superpower);
        
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
        
        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }
    @PostMapping("editSuperpower")
    public String performEditSuperpower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superpower superpower = superpowerDao.getSuperpowerById(id);
        superpower.setName(request.getParameter("name"));
        superpowerDao.updateSuperpower(superpower);
        
        return "redirect:/superpowers";
    }
    @GetMapping("superpowerDetail")
    public String courseDetail(Integer id, Model model) {
       Superpower superpower = superpowerDao.getSuperpowerById(id);
        model.addAttribute("superpower", superpower);
        return "superpowerDetail";
    }
}
