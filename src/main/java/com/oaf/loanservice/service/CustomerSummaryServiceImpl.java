package com.oaf.loanservice.service;

import com.oaf.loanservice.dao.CustomerSummaryRepository;
import com.oaf.loanservice.domain.CustomerSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSummaryServiceImpl implements CustomerSummaryService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerSummaryServiceImpl.class);

    private final CustomerSummaryRepository customerSummaryRepository;

    public CustomerSummaryServiceImpl(CustomerSummaryRepository customerSummaryRepository) {
        this.customerSummaryRepository = customerSummaryRepository;
    }

    @Override
    public int[] save(List<CustomerSummary> customerSummaries) {
        logger.info("event=SAVE_BULK_CUSTOMER_SUMMARY_START,  customerSummariesCount={}", customerSummaries.size());
        long start = System.currentTimeMillis();
        int[] savedSummaries = customerSummaryRepository.save(customerSummaries);

        logger.info("event=SAVE_BULK_CUSTOMER_SUMMARY_END, timeMS={}", System.currentTimeMillis() - start);

        return savedSummaries;
    }
}
