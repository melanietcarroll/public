/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.controllers;

import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import com.mc.guessthenumberrest.service.GuessTheNumberRESTServiceLayer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    public GuessTheNumberRESTController(GuessTheNumberRESTServiceLayer service) {
        this.service = service;
    }

    @GetMapping
    public List<Game> all() {
        return service.getAllGames();
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        String answer = service.createAnswer();
        Game newGame = new Game();
        newGame.setGameAnswer(answer);
        newGame.setStatus("active");
        service.addGame(newGame);
        return newGame.getId();
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Round> guess(@RequestBody Round round) {
        Round currentRound = new Round();

        Game currentGame = service.getCurrentGameById(round.getGameId());

        boolean containsCorrectDigits = service.containsFourDigits(round.getRoundGuess());

        boolean duplicate = service.hasDuplicateDigits(Integer.parseInt(round.getRoundGuess()));

        if (duplicate == false && containsCorrectDigits == true) {
            HashMap<String, String> roundResults = service.playRound(round.getRoundGuess(), currentGame.getGameAnswer());

            currentGame.setStatus(roundResults.values().toString());
            currentGame.setId(currentGame.getId());
            currentGame.setGameAnswer(currentGame.getGameAnswer());
            currentRound.setGameId(round.getGameId());
            currentRound.setRoundGuess(round.getRoundGuess());
            currentRound.setTimeOfGuess(LocalDateTime.now());
            currentRound.setResultOfGuess(roundResults.keySet().toString());

            service.updateGame(currentGame);
            service.addRound(currentRound);
            return ResponseEntity.ok(currentRound);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
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

        Game game = service.getGameById(id);
        if (game == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id not found"
            );
        }
        return service.getRoundsForGame(game);//must sort by time
    }

    @DeleteMapping("/{id}")

    public ResponseEntity deleteGameById(@PathVariable int id) {
        Game gameToDelete = service.getGameById(id);
        if (gameToDelete == null){
           return new ResponseEntity(HttpStatus.NOT_FOUND); 
        }
        List<Round> RoundList = service.getRoundsForGame(gameToDelete);
        if (RoundList == null) {
            service.deleteGameById(id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
        }
        for (Round r : RoundList) {
            service.deleteRoundById(r.getId());
        }
        if (service.deleteGameById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
