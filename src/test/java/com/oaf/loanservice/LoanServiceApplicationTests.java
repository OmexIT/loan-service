package com.oaf.loanservice;

import com.oaf.loanservice.service.RepaymentServiceImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Suite.SuiteClasses({
        RepaymentServiceImplTest.class
})
public class LoanServiceApplicationTests {

    @Test
    public void contextLoads() {
    }

}
