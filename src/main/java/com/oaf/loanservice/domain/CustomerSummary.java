
package com.oaf.loanservice.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CustomerID",
    "SeasonID",
    "Credit",
    "TotalRepaid"
})
public class CustomerSummary {

    @JsonProperty("CustomerID")
    private Integer customerID;
    @JsonProperty("SeasonID")
    private Integer seasonID;
    @JsonProperty("Credit")
    private Double credit;

    public CustomerSummary() {
    }

    public CustomerSummary(Integer customerID, Integer seasonID, Double credit, Double totalRepaid) {
        this.customerID = customerID;
        this.seasonID = seasonID;
        this.credit = credit;
        this.totalRepaid = totalRepaid;
    }

    @JsonProperty("TotalRepaid")
    private Double totalRepaid;
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

    @JsonProperty("Credit")
    public Double getCredit() {
        return credit;
    }

    @JsonProperty("Credit")
    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @JsonProperty("TotalRepaid")
    public Double getTotalRepaid() {
        return totalRepaid;
    }

    @JsonProperty("TotalRepaid")
    public void setTotalRepaid(Double totalRepaid) {
        this.totalRepaid = totalRepaid;
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
