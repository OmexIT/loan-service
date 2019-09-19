package com.oaf.loanservice.dao;

import com.oaf.loanservice.domain.CustomerSummary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerSummaryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerSummaryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(CustomerSummary customerSummary) {
        return jdbcTemplate.update(
                "INSERT INTO CustomerSummaries(CustomerID, SeasonID, Credit,TotalRepaid) VALUES (?, ?, ?, ?)",
                customerSummary.getCustomerID(), customerSummary.getSeasonID(), customerSummary.getCredit(),
                customerSummary.getTotalRepaid());
    }

    public int[] save(List<CustomerSummary> customerSummaries) {
        String sql = "INSERT INTO CustomerSummaries(CustomerID, SeasonID, Credit,TotalRepaid) VALUES (?, ?, ?, ?)";
        int[] rows = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CustomerSummary summary = customerSummaries.get(i);
                ps.setInt(1, summary.getCustomerID());
                ps.setInt(2, summary.getSeasonID());
                ps.setDouble(3, summary.getCredit());
                ps.setDouble(4, summary.getTotalRepaid());
            }

            @Override
            public int getBatchSize() {
                return customerSummaries.size();
            }
        });

        return rows;
    }

    public int update(CustomerSummary customerSummary) {
        return jdbcTemplate.update(
                "update CustomerSummaries set SeasonID = ?, Credit=?, TotalRepaid=? where CustomerID = ?",
                customerSummary.getSeasonID(), customerSummary.getCredit(), customerSummary.getTotalRepaid(),
                customerSummary.getCustomerID());
    }

    public int updateTotalRepaid(int customerId, double totalRepaid) {
        return jdbcTemplate.update(
                "update CustomerSummaries set TotalRepaid=? where CustomerID = ?",
                totalRepaid, customerId);
    }


    public int deleteByCustomerId(int id) {
        return jdbcTemplate.update(
                "delete from CustomerSummaries where CustomerID = ?",
                id);
    }

    public List<CustomerSummary> findAll() {
        return jdbcTemplate.query(
                "select * from CustomerSummaries",
                (rs, rowNum) ->
                        new CustomerSummary(
                                rs.getInt("CustomerID"),
                                rs.getInt("SeasonID"),
                                rs.getDouble("Credit"),
                                rs.getDouble("TotalRepaid")
                        )
        );
    }


    public Optional<CustomerSummary> findByCustomerID(int id) {
        return jdbcTemplate.queryForObject(
                "select * from books CustomerSummaries CustomerID = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new CustomerSummary(
                                rs.getInt("CustomerID"),
                                rs.getInt("SeasonID"),
                                rs.getDouble("Credit"),
                                rs.getDouble("TotalRepaid")
                        ))
        );
    }

    public Optional<CustomerSummary> findByCustomerIDAndSeasonId(int customerID, int seasonId) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from CustomerSummaries WHERE CustomerID = ? AND SeasonID = ?",
                    new Object[]{customerID, seasonId},
                    (rs, rowNum) ->
                            Optional.of(new CustomerSummary(
                                    rs.getInt("CustomerID"),
                                    rs.getInt("SeasonID"),
                                    rs.getDouble("Credit"),
                                    rs.getDouble("TotalRepaid")
                            ))
            );
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<CustomerSummary> findCustomerOutstandingCredit(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM CustomerSummaries a INNER JOIN Seasons b ON a.SeasonID = b.SeasonID WHERE (a.Credit-a.TotalRepaid) > 0 AND a.CustomerID =?  ORDER BY b.StartDate ASC",
                new Object[]{id},
                (rs, rowNum) ->
                        new CustomerSummary(
                                rs.getInt("CustomerID"),
                                rs.getInt("SeasonID"),
                                rs.getDouble("Credit"),
                                rs.getDouble("TotalRepaid")
                        )
        );
    }

    public Optional<CustomerSummary> findCustomerYoungestSeason(int id) {
        return Optional.empty();
    }
}
