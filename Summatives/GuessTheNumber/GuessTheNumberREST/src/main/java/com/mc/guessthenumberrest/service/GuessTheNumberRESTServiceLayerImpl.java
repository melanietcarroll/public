/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.service;

import com.mc.guessthenumberrest.data.GameDatabaseDao;
import com.mc.guessthenumberrest.data.RoundDatabaseDao;
import com.mc.guessthenumberrest.service.GuessTheNumberRESTServiceLayer;
import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Melanie Carroll
 */
@Service
public class GuessTheNumberRESTServiceLayerImpl implements GuessTheNumberRESTServiceLayer {

    GameDatabaseDao gameDao;
    RoundDatabaseDao roundDao;

    @Autowired
    public GuessTheNumberRESTServiceLayerImpl(GameDatabaseDao gameDao, RoundDatabaseDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @Override
    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public Game getGameById(int id) {
        return gameDao.getGameById(id);
    }

    @Override
    public void updateGame(Game game) {
        gameDao.updateGame(game);
    }

    @Override
    public boolean deleteGameById(int id) {
        return gameDao.deleteGameById(id);
    }

    @Override
    public Round addRound(Round round) {
        return roundDao.addRound(round);
    }

//    @Override
//    public Round getRoundByid(int id) {
//        return roundDao.getRoundByid(id);
//    }

    @Override
    public boolean deleteRoundById(int id) {
        return roundDao.deleteRoundById(id);
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        return roundDao.getRoundsForGame(game);
    }

    @Override
    public boolean hasDuplicateDigits(int num) {
        boolean[] numbers = new boolean[10];
        while (num > 0) {
            if (numbers[num % 10]) {
                return true;
            }
            numbers[num % 10] = true;
            num /= 10;
        }
        return false;
    }

    @Override
    public String createAnswer() {
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numberList.add(i);
        }

        Collections.shuffle(numberList);

        String result = "";
        for (int i = 0; i < 4; i++) {
            result += numberList.get(i).toString();
        }
        return result;

    }

    @Override
    public HashMap<String, String> playRound(String guess, String answer) {
        HashMap<String, String> results = new HashMap();
        String status = "active";
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                bulls++;
            } else if (answer.contains(guess.charAt(i) + "")) {
                cows++;
            }
        }
        if (bulls == 4) {
            status = "complete";
        }

        String bullsAndCows = "e: " + bulls + " : " + " p: " + cows;
        results.put(bullsAndCows, status);

        return results;
    }

    @Override
    public boolean containsFourDigits(String guess) {
        boolean containsCorrectDigits = false;
        char[] chars = guess.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
            if (chars.length == 4) {
                return containsCorrectDigits = true;
            }
        }
        return containsCorrectDigits;
    }

    @Override
    public Game getCurrentGameById(int id) {
        return gameDao.getCurrentGameById(id);
    }
}
