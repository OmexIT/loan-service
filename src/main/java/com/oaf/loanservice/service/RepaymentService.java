package com.oaf.loanservice.service;

import com.oaf.loanservice.domain.RepaymentUpload;

public interface RepaymentService {
    public void repay(RepaymentUpload repaymentUpload);

    public void adjustRepayment(int repaymentId, double adjustByValue);

    public void adjustRepayment(int repaymentId, double adjustByValue, int adjustToSeasonId);
}
