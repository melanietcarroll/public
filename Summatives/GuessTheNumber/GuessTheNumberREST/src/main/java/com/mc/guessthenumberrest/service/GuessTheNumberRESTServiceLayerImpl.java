/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.service;

import static com.mc.guessthenumberrest.App.createAnswer;
import static com.mc.guessthenumberrest.App.hasDuplicateDigits;
import com.mc.guessthenumberrest.data.GameDatabaseDao;
import com.mc.guessthenumberrest.data.RoundDatabaseDao;
import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Melanie Carroll
 */
public class GuessTheNumberRESTServiceLayerImpl implements GuessTheNumberRESTServiceLayer {

    GameDatabaseDao gameDao;
    RoundDatabaseDao roundDao;

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
    public boolean updateGame(Game game) {
        return gameDao.updateGame(game);
    }

    @Override
    public boolean deleteGameById(int id) {
        return gameDao.deleteGameById(id);
    }

    @Override
    public Round addRound(Round round) {
        return roundDao.addRound(round);
    }

    @Override
    public Round getRoundByid(int id) {
        return roundDao.getRoundByid(id);
    }

    @Override
    public boolean updateRound(Round round) {
        return roundDao.updateRound(round);
    }

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
    public HashMap<String, Boolean> playRound(String guess, String answer) {
//            Random gen = new Random();
//        String target = createAnswer();
//        while (hasDupes(target = (gen.nextInt(9000) + 1000)));
//        String targetStr = target + "";
        HashMap<String, Boolean> results = new HashMap();
        boolean guessed = false;
//        Scanner myScanner = new Scanner(System.in);
//        int guesses = 0;
//        do {
        int bulls = 0;
        int cows = 0;

//            System.out.println("Please enter a 4-digit number with no repeating digits: ");
//            int guess;
//            try {
//                guess = myScanner.nextInt();
//                if (hasDuplicateDigits(guess) || guess < 1000) {
//                    continue;
//                }
//            } catch (InputMismatchException e) {
//                continue;
//            }
//            guesses++;
//            String guessString = Integer.toString(guess);
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                bulls++;
            } else if (answer.contains(guess.charAt(i) + "")) {
                cows++;
            }
        }
        if (bulls == 4) {
            guessed = true;
        }
//            } else {
//                System.out.println(cows + " Cows and " + bulls + " Bulls.");
//            }
//        } while (!guessed);
//        System.out.println("You won after " + guesses + " guesses!");
        String bullsAndCows = "e: " + bulls + " : " + " p: " + cows;
        results.put(bullsAndCows, guessed);

        return results;
    }

}
