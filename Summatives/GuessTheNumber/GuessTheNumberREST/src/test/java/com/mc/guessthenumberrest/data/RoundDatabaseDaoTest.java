/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.data;

import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Melanie Carroll
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundDatabaseDaoTest {

    @Autowired
    GameDatabaseDao gameDao;

    @Autowired
    RoundDatabaseDao roundDao;

    public RoundDatabaseDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteRoundById(round.getId());
        }

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addRound method, of class RoundDatabaseDao.
     */
    @Test
    public void testAddRound() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        gameDao.addGame(game);

        Round round = new Round();
        round.setResultOfGuess("e:4:p:0");
        round.setGameId(game.getId());
        round.setRoundGuess("1234");
        round.setTimeOfGuess(LocalDateTime.now());
        round = roundDao.addRound(round);

        List<Round> rounds = roundDao.getRoundsForGame(game);

        assertEquals(1, rounds.size());
    }

    /**
     * Test of getGameForRound method, of class RoundDatabaseDao.
     */
    @Test
    public void testGetGameForRound() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        gameDao.addGame(game);

        Round round = new Round();
        round.setResultOfGuess("e:4:p:0");
        round.setGameId(game.getId());
        round.setRoundGuess("1234");
        round.setTimeOfGuess(LocalDateTime.now());
        round = roundDao.addRound(round);

        Game fromDao = roundDao.getGameForRound(round);

        assertEquals(game, fromDao);
    }

    /**
     * Test of deleteRoundById method, of class RoundDatabaseDao.
     */
    @Test
    public void testDeleteRoundById() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        gameDao.addGame(game);

        Round round = new Round();
        round.setResultOfGuess("e:4:p:0");
        round.setGameId(game.getId());
        round.setRoundGuess("1234");
        round.setTimeOfGuess(LocalDateTime.now());
        round = roundDao.addRound(round);

        roundDao.deleteRoundById(round.getId());

        List<Round> fromDao = roundDao.getRoundsForGame(game);
        assertEquals(0, fromDao.size());
    }

    /**
     * Test of getRoundsForGame method, of class RoundDatabaseDao.
     */
    @Test
    public void testGetRoundsForGame() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        gameDao.addGame(game);

        Round round = new Round();
        round.setResultOfGuess("e:4:p:0");
        round.setGameId(game.getId());
        round.setRoundGuess("1234");
        round.setTimeOfGuess(LocalDateTime.now());
        round = roundDao.addRound(round);

        Round round2 = new Round();
        round2.setResultOfGuess("e:2:p:0");
        round2.setGameId(game.getId());
        round2.setRoundGuess("5678");
        round2.setTimeOfGuess(LocalDateTime.now());
        round2 = roundDao.addRound(round2);

        List<Round> fromDao = roundDao.getRoundsForGame(game);
        assertEquals(2, fromDao.size());

    }

    /**
     * Test of getAllRounds method, of class RoundDatabaseDao.
     */
    @Test
    public void testGetAllRounds() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        gameDao.addGame(game);

        Round round = new Round();
        round.setResultOfGuess("e:4:p:0");
        round.setGameId(game.getId());
        round.setRoundGuess("1234");
        round.setTimeOfGuess(LocalDateTime.now());
        round = roundDao.addRound(round);

        Game game2 = new Game();
        game2.setGameAnswer(null);
        game2.setStatus("complete");
        gameDao.addGame(game2);

        Round round2 = new Round();
        round2.setResultOfGuess("e:2:p:0");
        round2.setGameId(game2.getId());
        round2.setRoundGuess("5678");
        round2.setTimeOfGuess(LocalDateTime.now());
        round2 = roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRounds();

        assertEquals(2, rounds.size());
    }

}
