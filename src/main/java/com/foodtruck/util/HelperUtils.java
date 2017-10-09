package com.foodtruck.util;

import com.foodtruck.bean.FoodTruck;
import com.foodtruck.bean.FoodTruckBean;

public class HelperUtils {

    /**
     * @param latitude1  Latitude of source location
     * @param longitude1 Longitude of source location
     * @param latitude2  Latitude of destination location
     * @param longitude2 Longitude of destination location
     * @return Distance in Meters
     */
    public static long getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        if (latitude1 == 0 || latitude2 == 0 || longitude1 == 0 || longitude2 == 0) {
            return 0;
        }

        double theta = longitude1 - longitude2;
        double dist = Math.sin(deg2rad(latitude1)) * Math.sin(deg2rad(latitude2)) + Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609.344;
        return (long) dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static FoodTruckBean convertFoodTruckToFoodTruckBean(FoodTruck foodTruck){

        FoodTruckBean foodTruckBean = new FoodTruckBean();
        foodTruckBean.setAddress(foodTruck.getAddress());
        foodTruckBean.setApplicant(foodTruck.getApplicant());
        foodTruckBean.setApproved(foodTruck.getApproved());
        foodTruckBean.setDaysHours(foodTruck.getDaysHours());

    }
}
