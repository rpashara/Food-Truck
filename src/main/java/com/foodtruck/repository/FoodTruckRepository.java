package com.foodtruck.repository;

import com.foodtruck.bean.FoodTruck;

public interface FoodTruckRepository {



    FoodTruck getNearFoodTrucks(String latitude, String longitude, String radius);
}
