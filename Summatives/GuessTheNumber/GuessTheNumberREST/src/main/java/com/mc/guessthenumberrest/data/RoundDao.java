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
    Round addRound(Round round);
//    List<Round> getAllRounds();
    Round getRoundByid(int id);
    // true if item exists and is updated
//    boolean updateRound(Round round);
     // true if item exists and is deleted
    boolean deleteRoundById(int id);
}
