package com.oaf.loanservice.service;

import com.oaf.loanservice.domain.Customer;

import java.util.List;

public interface CustomerService {

    public int[] save(List<Customer> customers);
}
