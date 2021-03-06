package com.foodtruck.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * Created by rpashara on 9/9/2017.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class FoodTruckBean{

    @JsonProperty("address")
    private String address;

    @JsonProperty("applicant")
    private String applicant;

    @JsonProperty("approved")
    private String approved;

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


    @JsonProperty("objectid")
    private String objectId;

    @JsonProperty("priorpermit")
    private String priorpermit;

    @JsonProperty("received")
    private String received;

    @JsonProperty("schedule")
    private String schedule;

    @JsonProperty("status")
    private String status;

    @JsonProperty("distance")
    private long distance;

}
