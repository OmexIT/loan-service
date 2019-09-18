package com.oaf.loanservice.dao;

import com.oaf.loanservice.domain.RepaymentUpload;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class RepaymentUploadRepository {

    private final JdbcTemplate jdbcTemplate;

    public RepaymentUploadRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RepaymentUpload save(RepaymentUpload repaymentUpload) {
        String sql = "INSERT INTO RepaymentUploads(CustomerID, SeasonID, Amount, Date) VALUES (?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();

        int recordAffected = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, repaymentUpload.getCustomerID());
                ps.setInt(2, repaymentUpload.getSeasonID());
                ps.setDouble(3, repaymentUpload.getAmount());
                ps.setDate(4, new Date(repaymentUpload.getDate().getTime()));
                return ps;
            }
        }, holder);

        if (recordAffected > 0) {
            repaymentUpload.setRepaymentUploadID(holder.getKey().intValue());
        }

        return repaymentUpload;
    }

    public List<RepaymentUpload> findAll() {
        return jdbcTemplate.query(
                "select * from RepaymentUploads",
                (rs, rowNum) ->
                        new RepaymentUpload(
                                rs.getInt("RepaymentUploadID"),
                                rs.getInt("SeasonID"),
                                rs.getDate("Date"),
                                rs.getDouble("Amount"),
                                rs.getInt("CustomerID")
                        )
        );
    }

    public Optional<RepaymentUpload> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from RepaymentUploads where RepaymentUploadID = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new RepaymentUpload(
                                rs.getInt("RepaymentUploadID"),
                                rs.getInt("SeasonID"),
                                rs.getDate("Date"),
                                rs.getDouble("Amount"),
                                rs.getInt("CustomerID")
                        ))
        );
    }
}
