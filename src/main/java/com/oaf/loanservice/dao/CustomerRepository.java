package com.oaf.loanservice.dao;

import com.oaf.loanservice.domain.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Customer customer) {
        return jdbcTemplate.update(
                "INSERT INTO Customers(CustomerID, CustomerName) VALUES (?, ?)",
                customer.getCustomerID(), customer.getCustomerName());
    }

    public int update(Customer customer) {
        return jdbcTemplate.update(
                "UPDATE Customers set CustomerName = ? where CustomerID = ?",
                customer.getCustomerName(), customer.getCustomerID());
    }


    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete from Customers where CustomerID = ?",
                id);
    }


    public List<Customer> findAll() {
        return jdbcTemplate.query(
                "select * from Customers",
                (rs, rowNum) ->
                        new Customer(
                                rs.getInt("CustomerID"),
                                rs.getString("CustomerName")
                        )
        );
    }


    public Optional<Customer> findById(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from Customers where CustomerID = ?",
                    new Object[]{id},
                    (rs, rowNum) ->
                            Optional.of(new Customer(
                                    rs.getInt("CustomerID"),
                                    rs.getString("CustomerName")
                            ))
            );
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
