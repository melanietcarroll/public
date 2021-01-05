/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.service;

import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Melanie Carroll
 */
public interface GuessTheNumberRESTServiceLayer {
    Game addGame(Game game);
    List<Game> getAllGames();
    Game getGameById(int id);
    Game getCurrentGameById(int id);
    void updateGame(Game game);
    boolean deleteGameById(int id);
    
    Round addRound(Round round);
    boolean deleteRoundById(int id);
    List<Round> getRoundsForGame(Game game);
    
    boolean hasDuplicateDigits(int num);
    String createAnswer();
    HashMap <String, String> playRound(String guess, String answer);
    boolean containsFourDigits(String guess);
}
