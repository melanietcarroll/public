/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Melanie Carroll
 */
public class Round {
    int id;
    String roundGuess;
    LocalDateTime timeOfGuess;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id;
        hash = 19 * hash + Objects.hashCode(this.roundGuess);
        hash = 19 * hash + Objects.hashCode(this.timeOfGuess);
        hash = 19 * hash + Objects.hashCode(this.resultOfGuess);
        hash = 19 * hash + this.gameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.roundGuess, other.roundGuess)) {
            return false;
        }
        if (!Objects.equals(this.resultOfGuess, other.resultOfGuess)) {
            return false;
        }
        if (!Objects.equals(this.timeOfGuess, other.timeOfGuess)) {
            return false;
        }
        return true;
    }

    
    String resultOfGuess;
    int gameId;

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

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

    public int getGameId() {
        return gameId;
    }
   
}
