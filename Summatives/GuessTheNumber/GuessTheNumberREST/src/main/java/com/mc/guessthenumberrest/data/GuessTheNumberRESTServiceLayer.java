/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.data;

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
    // true if item exists and is updated
    void updateGame(Game game);
     // true if item exists and is deleted
    boolean deleteGameById(int id);
    
    Round addRound(Round round);
//    List<Round> getAllRounds();
    Round getRoundByid(int id);
    // true if item exists and is updated
    boolean updateRound(Round round);
     // true if item exists and is deleted
    boolean deleteRoundById(int id);
    List<Round> getRoundsForGame(Game game);
    
    boolean hasDuplicateDigits(int num);
    String createAnswer();
    HashMap <String, String> playRound(String guess, String answer);
    boolean containsFourDigits(String guess);
}
