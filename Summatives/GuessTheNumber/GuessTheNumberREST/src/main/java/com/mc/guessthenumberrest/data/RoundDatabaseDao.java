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
import java.sql.Timestamp;
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
public class RoundDatabaseDao implements RoundDao{
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(roundGuess, timeOfGuess, resultOfGuess, gameId) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, round.getRoundGuess());
            Timestamp.valueOf(round.getTimeOfGuess());
            statement.setString(3, round.getResultOfGuess());
            
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public Round getRoundByid(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteRoundById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
       
       final String sql = "SELECT g.* FROM game g "
                + "JOIN round r ON g.id = r.gameId WHERE g.id = ? AND finished = true";
       return jdbcTemplate.query(sql, new RoundMapper());
    }
    
    private static final class RoundMapper implements RowMapper<Round> {
//not setting gameId since it is not part of the Round object?
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("id"));
            round.setRoundGuess(rs.getString("roundGuess"));
            round.setTimeOfGuess(rs.getTimestamp("timeOfGuess").toLocalDateTime());
            round.setResultOfGuess(rs.getString ("resultOfGuess"));
            return round;
        }
    }
    
}
