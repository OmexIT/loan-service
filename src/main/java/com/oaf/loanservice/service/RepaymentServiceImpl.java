package com.oaf.loanservice.service;

import com.oaf.loanservice.dao.CustomerSummaryRepository;
import com.oaf.loanservice.dao.RepaymentRepository;
import com.oaf.loanservice.domain.CustomerSummary;
import com.oaf.loanservice.domain.Repayment;
import com.oaf.loanservice.domain.RepaymentUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    private static final Logger logger = LoggerFactory.getLogger(RepaymentServiceImpl.class);

    private final CustomerSummaryRepository customerSummaryRepository;
    private final RepaymentRepository repaymentRepository;

    public RepaymentServiceImpl(CustomerSummaryRepository customerSummaryRepository,
                                RepaymentRepository repaymentRepository) {
        this.customerSummaryRepository = customerSummaryRepository;
        this.repaymentRepository = repaymentRepository;
    }

    /***
     * This method takes uploaded repayment record and allocates the amount paid to various seasons based on the
     * following rules:
     *     - If the season is specified in the upload, repay the whole amount to the specified season regardless
     *     - If the season is not specified allocate the to outstanding seasons in FIFO order, if there is overpayment
     *     then allocate the remainder amount to the latest customer season
     *     - If no outstanding credit, assign the repayment to the latest season
     * @param repaymentUpload Repayment record uploaded
     */
    @Override
    public void repay(RepaymentUpload repaymentUpload) {
        logger.info("event=REPAY_START, repaymentUpload={}", repaymentUpload);
        long start = System.currentTimeMillis();
        //If seasonId is not null, we apply all the payment to the season
        if (repaymentUpload.getSeasonID() != null) {
            Repayment repayment = new Repayment(repaymentUpload);
            logger.debug("event=REPAY_OVERRIDE, repayment={}", repayment);
            repaymentRepository.save(repayment);
        } else {
            //Get all customer season summary with outstanding credit
            List<CustomerSummary> customerOutstandingCredit =
                    customerSummaryRepository.findCustomerOutstandingCredit(repaymentUpload.getCustomerID());

            //Check if customer has outstanding credit and add the repayment to the latest loan
            if (customerOutstandingCredit.isEmpty()) {
                customerSummaryRepository.findCustomerYoungestSeason(repaymentUpload.getCustomerID())
                        .ifPresent(season -> {
                            Repayment repayment = new Repayment(repaymentUpload);
                            logger.debug("event=REPAY_NO_LOAN, repayment={}", repayment);
                            repayment.setSeasonID(season.getSeasonID());
                            repaymentRepository.save(repayment);
                        });
            } else {
                int lastIndex = customerOutstandingCredit.size() - 1;

                double uploadedAmountBalance = repaymentUpload.getAmount();
                logger.debug("event=REPAY_ALLOCATE, uploadedAmountBalance={}, lastIndex={}", uploadedAmountBalance, lastIndex);
                for (int i = 0; i < customerOutstandingCredit.size(); i++) {
                    CustomerSummary customerSummary = customerOutstandingCredit.get(i);
                    //Get outstanding balance
                    double outstandingBalance = customerSummary.getCredit() - customerSummary.getTotalRepaid();

                    //Check if lastIndex then allocate the remainder to the current repayment
                    if (i == lastIndex) {
                        Repayment repayment = new Repayment(repaymentUpload.getCustomerID(),
                                repaymentUpload.getSeasonID(), repaymentUpload.getDate(),
                                uploadedAmountBalance, repaymentUpload.getRepaymentUploadID());
                        logger.debug("event=REPAY_LAST, repayment={}", repayment);
                        repaymentRepository.save(repayment);
                    } else if (outstandingBalance > uploadedAmountBalance) {
                        //Note the amount to be paid is uploadedAmountBalance
                        Repayment repayment = new Repayment(repaymentUpload.getCustomerID(),
                                repaymentUpload.getSeasonID(), repaymentUpload.getDate(),
                                uploadedAmountBalance, repaymentUpload.getRepaymentUploadID());
                        logger.debug("event=REPAY_PART, repayment={}, outstandingBalance={}, uploadedAmountBalance={}", repayment, outstandingBalance, uploadedAmountBalance);
                        repaymentRepository.save(repayment);
                        break;
                    } else {
                        Repayment repayment = new Repayment(repaymentUpload.getCustomerID(),
                                repaymentUpload.getSeasonID(), repaymentUpload.getDate(),
                                outstandingBalance, repaymentUpload.getRepaymentUploadID());
                        logger.debug("event=REPAY_FULL_ALLOCATE, repayment={}, outstandingBalance={}, uploadedAmountBalance={}", repayment, outstandingBalance, uploadedAmountBalance);
                        repaymentRepository.save(repayment);
                        uploadedAmountBalance = uploadedAmountBalance - outstandingBalance;
                    }

                }
            }

        }
        logger.info("event=REPAY_END, timeMS={}", System.currentTimeMillis() - start);
    }
}
