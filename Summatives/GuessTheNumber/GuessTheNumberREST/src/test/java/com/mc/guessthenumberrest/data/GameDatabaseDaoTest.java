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
import static org.junit.Assert.assertNotEquals;
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
public class GameDatabaseDaoTest {

    @Autowired
    GameDatabaseDao gameDao;

    @Autowired
    RoundDatabaseDao roundDao;

    public GameDatabaseDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getId());
        }

        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteRoundById(round.getId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class GameDatabaseDao.
     */
    @Test
    public void testAddGame() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getGameById(game.getId());

        assertEquals(game, fromDao);
    }

    /**
     * Test of getAllGames method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        gameDao.addGame(game);

        Game game2 = new Game();
        game2.setGameAnswer(null);
        game2.setStatus("complete");
        gameDao.addGame(game2);

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    /**
     * Test of getGameById method, of class GameDatabaseDao.
     */
    @Test
    public void testGetGameById() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getCurrentGameById(game.getId());

        assertEquals(game, fromDao);
    }

    /**
     * Test of updateGame method, of class GameDatabaseDao.
     */
    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getCurrentGameById(game.getId());

        assertEquals(game, fromDao);

        game.setStatus("complete");

        gameDao.updateGame(game);

        assertNotEquals(game, fromDao);

        fromDao = gameDao.getGameById(game.getId());

        assertEquals(game, fromDao);
    }

    /**
     * Test of deleteGameById method, of class GameDatabaseDao.
     */
    @Test
    public void testDeleteGameById() {
        Game game = new Game();
        game.setGameAnswer(null);
        game.setStatus("active");
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setResultOfGuess("e:2:p:0");
        round.setRoundGuess("4567");
        round.setGameId(game.getId());
        round.setTimeOfGuess(LocalDateTime.now());
        round = roundDao.addRound(round);

        gameDao.deleteGameById(game.getId());

        Game fromDao = gameDao.getGameById(game.getId());
        assertNull(fromDao);
    }

    /**
     * Test of getCurrentGameById method, of class GameDatabaseDao.
     */
    @Test
    public void testGetCurrentGameById() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setStatus("active");
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getCurrentGameById(game.getId());

        assertEquals(game, fromDao);
    }

}
