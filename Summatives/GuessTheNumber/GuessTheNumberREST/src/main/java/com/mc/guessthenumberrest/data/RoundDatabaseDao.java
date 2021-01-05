/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest.data;

import com.mc.guessthenumberrest.models.Game;
import com.mc.guessthenumberrest.models.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mc.guessthenumberrest.data.GameDatabaseDao.GameMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.jdbc.support.GeneratedKeyHolder;

/**
 *
 * @author Melanie Carroll
 */
@Repository
public class RoundDatabaseDao implements RoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Round addRound(Round round) {

        final String INSERT_ROUND = "INSERT INTO round(roundGuess, timeOfGuess, resultOfGuess, gameId) VALUES(?,?,?,?);";
        jdbcTemplate.update(INSERT_ROUND,
                round.getRoundGuess(),
                Timestamp.valueOf(round.getTimeOfGuess()),
                round.getResultOfGuess(),
                round.getGameId());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setId(newId);

        return round;
    }

//    @Override
//    public Round getRoundByid(int id) { //is this used?
//        try {
//            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE id = ?";
//            Round round = jdbcTemplate.queryForObject(SELECT_ROUND_BY_ID,
//                    new RoundMapper(), id);
//            round.setGameId(id);
//            return round;
//        } catch (DataAccessException ex) {
//            return null;
//        }
//    }

    public Game getGameForRound(Round round) {
        final String SELECT_GAME_FOR_ROUND = "SELECT g.* FROM game g "
                + "JOIN round r ON g.id = r.gameId WHERE r.id = ?";
        return jdbcTemplate.queryForObject(SELECT_GAME_FOR_ROUND, new GameMapper(),
                round.getId());
    }

    @Override
    public boolean deleteRoundById(int id) {
        final String DELETE_ROUND = "DELETE FROM round WHERE id = ?";
        return jdbcTemplate.update(DELETE_ROUND, id) > 0;
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        final String sql = "SELECT r.* FROM round r JOIN game g ON g.id = r.gameId WHERE g.id = ? ORDER BY timeOfGuess;";
        return jdbcTemplate.query(sql, new RoundMapper(), game.getId());
    }



    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {

            Round currentRound = new Round();
            currentRound.setId(rs.getInt("id"));
            currentRound.setRoundGuess(rs.getString("roundGuess"));
            currentRound.setTimeOfGuess(rs.getTimestamp("timeOfGuess").toLocalDateTime());
            currentRound.setResultOfGuess(rs.getString("resultOfGuess"));
            currentRound.setGameId(rs.getInt("gameId"));
            return currentRound;
        }
    }

}
