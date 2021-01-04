/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.controllers;

import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import com.mc.guessthenumberrest.data.GuessTheNumberRESTServiceLayer;
import static java.lang.Boolean.parseBoolean;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<Game> all() {       //working
        return service.getAllGames();
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {                //working
        String answer = service.createAnswer();
        Boolean finished = false;
        Game newGame = new Game();
        newGame.setGameAnswer(answer);
        newGame.setFinished(finished);
        service.addGame(newGame);
        return newGame.getId();
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Round> guess(String roundGuess, int gameId) {

        HashMap<String, Boolean> roundResults = new HashMap();
        Round currentRound = new Round();
        
       Game currentGame = service.getCurrentGameById(gameId);

        boolean containsCorrectDigits = service.containsFourDigits(roundGuess);

        boolean duplicate = service.hasDuplicateDigits(Integer.parseInt(roundGuess));

        if (duplicate == false && containsCorrectDigits == true) {
            roundResults = service.playRound(roundGuess, currentGame.getGameAnswer());

            String resultFinished = roundResults.values().toString();
            currentGame.setFinished(parseBoolean(resultFinished));
            currentRound.setGame(currentGame);
            currentRound.setRoundGuess(roundGuess);
            currentRound.setTimeOfGuess(LocalDateTime.now());
            currentRound.setResultOfGuess(roundResults.keySet().toString());
            currentRound.setId(5);
            service.updateGame(currentGame);
            service.addRound(currentRound);
            return ResponseEntity.ok(currentRound);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("game/{id}") //working
    public ResponseEntity<Game> findGameById(@PathVariable int id
    ) {
        Game result = service.getGameById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("rounds/{id}")  //working
    public List<Round> findRoundsForGameByGameId(@PathVariable int id){
   
        Game game = service.getGameById(id);
        if (game == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id not found"
            );
        }
        List result = service.getRoundsForGame(game);//must sort by time
        return result;
    }
    
    @DeleteMapping("/{id}") //working 
    
    public ResponseEntity deleteGameById(@PathVariable int id) {
        List<Round> RoundList = new ArrayList<Round>();
        Game gameToDelete = service.getGameById(id);
        RoundList = service.getRoundsForGame(gameToDelete);
        if (RoundList == null) {
            service.deleteGameById(id);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id not found"
            );
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
