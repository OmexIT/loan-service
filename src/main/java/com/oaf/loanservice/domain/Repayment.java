package com.oaf.loanservice.domain;

import java.util.Date;

public class Repayment {
    private Integer RepaymentID;
    private int CustomerID;
    private int SeasonID;
    private Date date;
    private double Amount;
    private int ParentID;

    public Repayment() {
    }

    public Repayment(int customerID, int seasonID, Date date, double amount, int parentID) {
        CustomerID = customerID;
        SeasonID = seasonID;
        this.date = date;
        Amount = amount;
        ParentID = parentID;
    }

    public Repayment(Integer repaymentID, int customerID, int seasonID, Date date, double amount, int parentID) {
        this(customerID, seasonID, date, amount, parentID);
        RepaymentID = repaymentID;
    }

    public Repayment(RepaymentUpload repaymentUpload) {
        this(repaymentUpload.getCustomerID(), repaymentUpload.getSeasonID(), repaymentUpload.getDate(),
                repaymentUpload.getAmount(), repaymentUpload.getRepaymentUploadID());
    }

    public Integer getRepaymentID() {
        return RepaymentID;
    }

    public void setRepaymentID(Integer repaymentID) {
        RepaymentID = repaymentID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getSeasonID() {
        return SeasonID;
    }

    public void setSeasonID(int seasonID) {
        SeasonID = seasonID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }
}
