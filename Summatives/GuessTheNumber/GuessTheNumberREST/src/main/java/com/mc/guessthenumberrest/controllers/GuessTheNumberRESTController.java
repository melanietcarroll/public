/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.controllers;

import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import com.mc.guessthenumberrest.service.GuessTheNumberRESTServiceLayer;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Melanie Carroll
 */
@RestController
@RequestMapping("/api/guessthenumber")
public class GuessTheNumberRESTController {

    private final GuessTheNumberRESTServiceLayer service;

    public GuessTheNumberRESTController(GuessTheNumberRESTServiceLayer service) {
        this.service = service;
    }

    @GetMapping
    public List<Game> all() {
        return service.getAllGames();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int begin(@RequestBody ToDo todo) {
        return dao.add(todo);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int guess(@RequestBody ToDo todo) {
        return dao.add(todo);
    }

    @GetMapping("game/{id}")
    public ResponseEntity<Game> findGameById(@PathVariable int id) {
        Game result = service.getGameById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("rounds/{id}")
    public List<Round> findRoundsForGameByGameId(@PathVariable int id) {
//        Game game = service.getGameById(id);
        List result = service.getRoundsForGame(service.getGameById(id));
        if (result == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id not found"
            );
//            return null;
        }
        return result;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody ToDo todo) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != todo.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (dao.update(todo)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (dao.deleteById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
