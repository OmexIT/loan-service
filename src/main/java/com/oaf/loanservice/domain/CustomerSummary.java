package com.oaf.loanservice.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerSummary {
    private int customerID;
    private int seasonID;

    private double credit;
    private double totalRepaid;

    public CustomerSummary() {
    }

    public CustomerSummary(int customerID, int seasonID, double credit, double totalRepaid) {
        this.customerID = customerID;
        this.seasonID = seasonID;
        this.credit = credit;
        this.totalRepaid = totalRepaid;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getTotalRepaid() {
        return totalRepaid;
    }

    public void setTotalRepaid(double totalRepaid) {
        this.totalRepaid = totalRepaid;
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
