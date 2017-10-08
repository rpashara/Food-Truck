package com.foodtruck.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*{
        "address": "400 HOWARD ST",
        "applicant": "Slider Shack, LLC.",
        "approved": "2017-04-04T00:00:00.000",
        "block": "3720",
        "blocklot": "3720008",
        "cnn": "7032000",
        "dayshours": "Mo/Th/Fr:10AM-3PM",
        "expirationdate": "2018-03-15T00:00:00.000",
        "facilitytype": "Truck",
        "fooditems": "Sliders: Tater Tots: Beverages",
        "latitude": "37.7891192076677",
        "location": {
        "type": "Point",
        "coordinates": [
        -122.395881039335,
        37.789119207668
        ]
        },
        "locationdescription": "HOWARD ST: FREMONT ST to 01ST ST (400 - 499)",
        "longitude": "-122.395881039335",
        "lot": "008",
        "objectid": "932023",
        "permit": "17MFF-0009",
        "priorpermit": "0",
        "received": "2017-02-03",
        "schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=17MFF-0009&ExportPDF=1&Filename=17MFF-0009_schedule.pdf",
        "status": "APPROVED",
        "x": "6013858.05956",
        "y": "2115347.09492"
        }*/


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
