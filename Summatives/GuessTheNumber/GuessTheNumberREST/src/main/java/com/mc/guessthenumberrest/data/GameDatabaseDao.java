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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
        final String sql = "INSERT INTO game(gameAnswer, finished) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getGameAnswer());
            statement.setBoolean(2, game.getFinished());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAllGames() {
         final String sql = "SELECT * FROM game WHERE finished = true;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game getGameByid(int id) {
        final String sql = "SELECT id, gameAnswer, finished "
                + "FROM game WHERE id = ? AND finished = true;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public boolean updateGame(Game game) {
        final String sql = "UPDATE game SET "
                + "gameAnswer = ?, "
                + "finished = ? "
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                game.getGameAnswer(),
                game.getFinished(), //example isFinished?
                game.getId()) > 0;
    }

    @Override
    public boolean deleteGameById(int id) {
        final String sql = "DELETE FROM game WHERE id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setGameAnswer(rs.getString("gameAnswer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
    
}
