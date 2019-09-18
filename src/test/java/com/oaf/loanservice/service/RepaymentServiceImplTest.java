package com.oaf.loanservice.service;

import com.oaf.loanservice.dao.CustomerRepository;
import com.oaf.loanservice.dao.CustomerSummaryRepository;
import com.oaf.loanservice.dao.RepaymentUploadRepository;
import com.oaf.loanservice.dao.SeasonRepository;
import com.oaf.loanservice.domain.Customer;
import com.oaf.loanservice.domain.CustomerSummary;
import com.oaf.loanservice.domain.RepaymentUpload;
import com.oaf.loanservice.domain.Season;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class RepaymentServiceImplTest {

    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerSummaryRepository customerSummaryRepository;
    @Autowired
    private RepaymentUploadRepository repaymentUploadRepository;
    @Autowired
    private RepaymentService repaymentService;

    @PostConstruct
    public void init() {
        if(!seasonRepository.findById(1).isPresent()) {
            seasonRepository.save(new Season(1, "Test season", new Date(), null));
        }
        if(!customerRepository.findById(1).isPresent()) {
            customerRepository.save(new Customer(1, "Test Customer 1"));
        }
        if(!customerSummaryRepository.findByCustomerIDAndSeasonId(1, 1).isPresent()) {
            customerSummaryRepository.save(new CustomerSummary(1, 1, 200, 0));
        }
    }

    @Test
    public void testRepayOverride() {
        seasonRepository.findById(1).ifPresent(System.err::println);
        customerRepository.findById(1).ifPresent(System.err::println);
        customerSummaryRepository.findCustomerYoungestSeason(1).ifPresent(System.err::println);

        RepaymentUpload repaymentUpload = repaymentUploadRepository.save(new RepaymentUpload(null, 1, new Date(), 300, 1));
        System.err.println(repaymentUpload);
        repaymentService.repay(repaymentUpload);
        CustomerSummary customerSummary = customerSummaryRepository.findByCustomerIDAndSeasonId(1, 1).get();
        System.err.println(customerSummary);
    }

    @Test
    public void testRepay2() {
        RepaymentUpload save = repaymentUploadRepository.save(new RepaymentUpload(null, 1, new Date(), 300, 1));
        System.err.println(save);
    }
}
