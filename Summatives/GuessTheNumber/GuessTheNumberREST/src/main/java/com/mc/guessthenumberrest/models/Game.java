/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.models;

/**
 *
 * @author Melanie Carroll
 */
public class Game {
     int id;
     String gameAnswer;
     Boolean finished;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameAnswer() {
        return gameAnswer;
    }

    public void setGameAnswer(String gameAnswer) {
        this.gameAnswer = gameAnswer;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
     
}
