package com.oaf.loanservice.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;


public class RepaymentUpload {

    private Integer repaymentUploadID;
    private Integer seasonID;
    private Date date;
    private double amount;
    private int customerID;

    public RepaymentUpload() {
    }

    public RepaymentUpload(Integer repaymentUploadID, Integer seasonID, Date date, double amount, int customerID) {
        this.repaymentUploadID = repaymentUploadID;
        this.seasonID = seasonID;
        this.date = date;
        this.amount = amount;
        this.customerID = customerID;
    }

    public Integer getRepaymentUploadID() {
        return repaymentUploadID;
    }

    public void setRepaymentUploadID(Integer repaymentUploadID) {
        this.repaymentUploadID = repaymentUploadID;
    }

    //Season Id is optional
    public Integer getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(Integer seasonID) {
        this.seasonID = seasonID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
