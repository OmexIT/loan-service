package com.oaf.loanservice.service;

import com.oaf.loanservice.domain.CustomerSummary;

import java.util.List;

public interface CustomerSummaryService {

    public int[] save(List<CustomerSummary> customerSummaries);
}
