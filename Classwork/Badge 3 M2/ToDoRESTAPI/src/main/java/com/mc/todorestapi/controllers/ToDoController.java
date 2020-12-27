/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.todorestapi.controllers;

import com.mc.todorestapi.data.ToDoDao;
import com.mc.todorestapi.models.ToDo;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Melanie Carroll
 */
@RestController
@RequestMapping("/api/todo")
public class ToDoController {
     private final ToDoDao dao;

    public ToDoController(ToDoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<ToDo> all() {
        return dao.getAll();
    }
}
