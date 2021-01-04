/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.models;

import java.time.LocalDateTime;

/**
 *
 * @author Melanie Carroll
 */
public class Round {
    int id;
    String roundGuess;
    LocalDateTime timeOfGuess;
    String resultOfGuess;
    Game game;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoundGuess() {
        return roundGuess;
    }

    public void setRoundGuess(String roundGuess) {
        this.roundGuess = roundGuess;
    }

    public LocalDateTime getTimeOfGuess() {
        return timeOfGuess;
    }

    public void setTimeOfGuess(LocalDateTime timeOfGuess) {
        this.timeOfGuess = timeOfGuess;
    }

    public String getResultOfGuess() {
        return resultOfGuess;
    }

    public void setResultOfGuess(String resultOfGuess) {
        this.resultOfGuess = resultOfGuess;
    }
    
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
