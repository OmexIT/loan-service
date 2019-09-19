package com.oaf.loanservice.dao;

import com.oaf.loanservice.domain.Season;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class SeasonRepository {

    private final JdbcTemplate jdbcTemplate;

    public SeasonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Season season) {
        return jdbcTemplate.update(
                "INSERT INTO Seasons(SeasonID, SeasonName, StartDate, EndDate) VALUES (?, ?, ?, ?)",
                season.getSeasonID(), season.getSeasonName(), season.getStartDate(), season.getEndDate());
    }

    public int[] save(List<Season> seasons) {
        String sql = "INSERT INTO Seasons(SeasonID, SeasonName, StartDate, EndDate) VALUES (?, ?, ?, ?)";
        int[] rows = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Season season = seasons.get(i);
                ps.setInt(1, season.getSeasonID());
                ps.setString(2, season.getSeasonName());
                ps.setDate(3, new Date(season.getStartDate().getTime()));
                ps.setDate(4, new Date(season.getEndDate().getTime()));
            }

            @Override
            public int getBatchSize() {
                return seasons.size();
            }
        });

        return rows;
    }

    public int update(Season season) {
        return jdbcTemplate.update(
                "update Seasons set SeasonName = ?, StartDate=?, EndDate=? where SeasonID = ?",
                season.getSeasonName(),
                season.getStartDate(),
                season.getEndDate(),
                season.getSeasonID());
    }


    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete from Seasons where SeasonID = ?",
                id);
    }

    public List<Season> findAll() {
        return jdbcTemplate.query(
                "select * from Seasons",
                (rs, rowNum) ->
                        new Season(
                                rs.getInt("SeasonID"),
                                rs.getString("SeasonName"),
                                rs.getDate("StartDate"),
                                rs.getDate("SeasonID")
                        )
        );
    }

    public Optional<Season> findById(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from Seasons where SeasonID = ?",
                    new Object[]{id},
                    (rs, rowNum) ->
                            Optional.of(new Season(
                                    rs.getInt("SeasonID"),
                                    rs.getString("SeasonName"),
                                    rs.getDate("StartDate"),
                                    rs.getDate("EndDate")
                            ))
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
