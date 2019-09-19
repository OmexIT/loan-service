
package com.oaf.loanservice.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Seasons",
        "Customers",
        "CustomerSummaries",
        "RepaymentUploads"
})
public class SeedUpload {

    @JsonProperty("Seasons")
    private List<Season> seasons = null;
    @JsonProperty("Customers")
    private List<Customer> customers = null;
    @JsonProperty("CustomerSummaries")
    private List<CustomerSummary> customerSummaries = null;
    @JsonProperty("RepaymentUploads")
    private List<RepaymentUpload> repaymentUploads = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Seasons")
    public List<Season> getSeasons() {
        return seasons;
    }

    @JsonProperty("Seasons")
    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    @JsonProperty("Customers")
    public List<Customer> getCustomers() {
        return customers;
    }

    @JsonProperty("Customers")
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @JsonProperty("CustomerSummaries")
    public List<CustomerSummary> getCustomerSummaries() {
        return customerSummaries;
    }

    @JsonProperty("CustomerSummaries")
    public void setCustomerSummaries(List<CustomerSummary> customerSummaries) {
        this.customerSummaries = customerSummaries;
    }

    @JsonProperty("RepaymentUploads")
    public List<RepaymentUpload> getRepaymentUploads() {
        return repaymentUploads;
    }

    @JsonProperty("RepaymentUploads")
    public void setRepaymentUploads(List<RepaymentUpload> repaymentUploads) {
        this.repaymentUploads = repaymentUploads;
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
