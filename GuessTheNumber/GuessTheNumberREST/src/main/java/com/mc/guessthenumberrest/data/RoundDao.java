/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.data;

import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import java.util.List;

/**
 *
 * @author Melanie Carroll
 */
public interface RoundDao {
    List<Round> getAllRounds();
    Round addRound(Round round);
    boolean deleteRoundById(int id);
    List<Round> getRoundsForGame(Game game);
}
