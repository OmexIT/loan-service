
package com.oaf.loanservice.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "SeasonID",
    "SeasonName",
    "StartDate",
    "EndDate"
})
public class Season {

    @JsonProperty("SeasonID")
    private Integer seasonID;
    @JsonProperty("SeasonName")
    private String seasonName;
    @JsonProperty("StartDate")
    @JsonFormat(pattern="M/d/yyyy")
    private Date startDate;
    @JsonProperty("EndDate")
    @JsonFormat(pattern="M/d/yyyy")
    private Date endDate;

    public Season() {
    }

    public Season(Integer seasonID, String seasonName, Date startDate, Date endDate) {
        this.seasonID = seasonID;
        this.seasonName = seasonName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("SeasonID")
    public Integer getSeasonID() {
        return seasonID;
    }

    @JsonProperty("SeasonID")
    public void setSeasonID(Integer seasonID) {
        this.seasonID = seasonID;
    }

    @JsonProperty("SeasonName")
    public String getSeasonName() {
        return seasonName;
    }

    @JsonProperty("SeasonName")
    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    @JsonProperty("StartDate")
    public Date getStartDate() {
        return startDate;
    }

    @JsonProperty("StartDate")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("EndDate")
    public Date getEndDate() {
        return endDate;
    }

    @JsonProperty("EndDate")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
