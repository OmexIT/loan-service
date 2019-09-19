package com.oaf.loanservice.service;

import com.oaf.loanservice.dao.CustomerRepository;
import com.oaf.loanservice.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /***
     * Service method to bulk insert customers
     *
     * @param customers list of customers
     * @return affected records
     */
    @Override
    public int[] save(List<Customer> customers) {
        logger.info("event=SAVE_BULK_CUSTOMER_START,  customersCount={}", customers.size());
        long start = System.currentTimeMillis();

        int[] savedCustomers = customerRepository.save(customers);

        logger.info("event=SAVE_BULK_CUSTOMER_END, savedCustomers={}, timeMS={}", savedCustomers, System.currentTimeMillis() - start);

        return savedCustomers;
    }
}
