
package com.oaf.loanservice.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "CustomerID",
        "SeasonID",
        "Date",
        "Amount",
        "RepaymentUploadID"
})
public class RepaymentUpload {
    public Integer getRepaymentUploadID() {
        return repaymentUploadID;
    }

    public void setRepaymentUploadID(Integer repaymentUploadID) {
        this.repaymentUploadID = repaymentUploadID;
    }

    public RepaymentUpload() {
    }

    public RepaymentUpload(Integer customerID, Integer seasonID, Date date, Double amount) {
        this(null,  customerID,  seasonID,  date,  amount);
    }

    public RepaymentUpload(Integer repaymentUploadID, Integer customerID, Integer seasonID, Date date, Double amount) {
        this.repaymentUploadID = repaymentUploadID;
        this.customerID = customerID;
        this.seasonID = seasonID;
        this.date = date;
        this.amount = amount;
    }

    @JsonProperty("RepaymentUploadID")
    private Integer repaymentUploadID;
    @JsonProperty("CustomerID")
    private Integer customerID;
    @JsonProperty("SeasonID")
    private Integer seasonID;
    @JsonProperty("Date")
    @JsonFormat(pattern="M/d/yyyy")
    private Date date;
    @JsonProperty("Amount")
    private Double amount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CustomerID")
    public Integer getCustomerID() {
        return customerID;
    }

    @JsonProperty("CustomerID")
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("SeasonID")
    public Integer getSeasonID() {
        return seasonID;
    }

    @JsonProperty("SeasonID")
    public void setSeasonID(Integer seasonID) {
        this.seasonID = seasonID;
    }

    @JsonProperty("Date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("Amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("Amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
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
