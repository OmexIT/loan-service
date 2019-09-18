package com.oaf.loanservice.dao;

import com.oaf.loanservice.domain.Season;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
