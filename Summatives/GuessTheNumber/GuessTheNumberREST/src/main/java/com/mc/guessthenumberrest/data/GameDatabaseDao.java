/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.data;

import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Melanie Carroll
 */
@Repository
public class GameDatabaseDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game addGame(Game game) {
        final String ADD_GAME = "INSERT INTO game(gameAnswer, status) VALUES(?,?);";

        jdbcTemplate.update(ADD_GAME,
                game.getGameAnswer(),
                game.getStatus());

        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setId(newId);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String GET_ALL_GAMES = "SELECT * FROM game;";
        List<Game> gameList = jdbcTemplate.query(GET_ALL_GAMES, new GameMapper());
        List<Game> filterResults = new ArrayList();
        for (Game g : gameList) {
            Game currentGame = new Game();
            if (g.getStatus().equals("active")){
                currentGame.setId(g.getId());    
                currentGame.setStatus(g.getStatus());
                filterResults.add(currentGame);
            }else{
            currentGame.setId(g.getId());
            currentGame.setGameAnswer(g.getGameAnswer());
            currentGame.setStatus(g.getStatus());
            filterResults.add(currentGame);
            }
        }
        return filterResults;
    }

    @Override
    public Game getGameById(int id) {
        try {
            final String sql = "SELECT id, gameAnswer, status "
                    + "FROM game WHERE id = ?;";
            Game retrievedGame = jdbcTemplate.queryForObject(sql, new GameMapper(), id);
            Game filteredFieldsGame = new Game();
            if (retrievedGame.getStatus().equals("active")){
                filteredFieldsGame.setId(retrievedGame.getId());    
                filteredFieldsGame.setStatus(retrievedGame.getStatus());
            }else{
            filteredFieldsGame.setId(retrievedGame.getId());
            filteredFieldsGame.setGameAnswer(retrievedGame.getGameAnswer());
            filteredFieldsGame.setStatus(retrievedGame.getStatus());
            }
            return filteredFieldsGame;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET gameAnswer = ?, status = ? WHERE id = ?;";

        jdbcTemplate.update(UPDATE_GAME,
                game.getGameAnswer(),
                game.getStatus(), //example isFinished?
                game.getId());
    }

    @Override
    public boolean deleteGameById(int id) {

        final String DELETE_ROUND_BY_GAME = "DELETE FROM round WHERE gameId = ?";
        jdbcTemplate.update(DELETE_ROUND_BY_GAME, id);

        final String DELETE_GAME = "DELETE FROM game WHERE id = ?";
        return jdbcTemplate.update(DELETE_GAME, id) > 0;
    }

    @Override
    public Game getCurrentGameById(int id) {
        try {
            final String sql = "SELECT id, gameAnswer, status FROM game WHERE id = ?;";
            return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setGameAnswer(rs.getString("gameAnswer"));
            game.setStatus(rs.getString("status"));
            return game;
        }
    }

}
