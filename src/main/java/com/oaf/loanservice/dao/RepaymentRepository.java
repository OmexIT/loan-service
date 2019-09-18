package com.oaf.loanservice.dao;

import com.oaf.loanservice.domain.Repayment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    public RepaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Repayment repayment) {
        return jdbcTemplate.update(
                "INSERT INTO Repayments (CustomerID, SeasonID, Amount, ParentID) VALUES (?, ?, ?, ?)",
                repayment.getCustomerID(), repayment.getSeasonID(), repayment.getAmount(), repayment.getParentID());
    }


    public int update(Repayment repayment) {
        return jdbcTemplate.update(
                "update Repayments set CustomerID = ?, Amount=?, SeasonID=?, ParentID=?, Date=? where RepaymentID = ?",
                repayment.getCustomerID(),
                repayment.getAmount(),
                repayment.getSeasonID(),
                repayment.getParentID(),
                repayment.getDate(),
                repayment.getRepaymentID());
    }


    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete from Repayments where RepaymentID = ?",
                id);
    }

    public List<Repayment> findAll() {
        return jdbcTemplate.query(
                "select * from Repayments",
                (rs, rowNum) ->
                        new Repayment(
                                rs.getInt("RepaymentID"),
                                rs.getInt("CustomerID"),
                                rs.getInt("SeasonID"),
                                rs.getDate("Date"),
                                rs.getDouble("Amount"),
                                rs.getInt("ParentID")
                        )
        );
    }

    public Optional<Repayment> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from Repayments where RepaymentID = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Repayment(
                                rs.getInt("RepaymentID"),
                                rs.getInt("CustomerID"),
                                rs.getInt("SeasonID"),
                                rs.getDate("Date"),
                                rs.getDouble("Amount"),
                                rs.getInt("ParentID")
                        ))
        );
    }
}
