package com.foodtruck.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by rpashara on 9/9/2017.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class FoodTruck {

    @JsonProperty("address")
    private String address;

    @JsonProperty("applicant")
    private String applicant;

    @JsonProperty("approved")
    private String approved;

    @JsonProperty("block")
    private String block;

    @JsonProperty("blocklot")
    private String blockLot;

    @JsonProperty("cnn")
    private String cnn;

    @JsonProperty("dayshours")
    private String daysHours;

    @JsonProperty("expirationdate")
    private String expirationDate;

    @JsonProperty("facilitytype")
    private String facilityType;

    @JsonProperty("fooditems")
    private String foodItems;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("locationdescription")
    private String locationDescription;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("lot")
    private String lot;

    @JsonProperty("objectid")
    private String objectId;

    @JsonProperty("permit")
    private String permit;

    @JsonProperty("priorpermit")
    private String priorpermit;

    @JsonProperty("received")
    private String received;

    @JsonProperty("schedule")
    private String schedule;

    @JsonProperty("status")
    private String status;

    @JsonProperty("x")
    private String x;

    @JsonProperty("y")
    private String y;

    /*private boolean isApproved(){
        // TODO: 10/8/2017 check for APPROVED status
        return true;
    }

    private String[] getFoodItems() {
        return this.fooditems.split(":");
    }*/
}
