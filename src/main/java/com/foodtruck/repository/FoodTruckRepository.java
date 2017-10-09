package com.foodtruck.repository;

import com.foodtruck.bean.FoodTruck;
import rx.Observable;

import java.util.Optional;

public interface FoodTruckRepository {



    Observable<Optional<FoodTruck>> getNearFoodTrucks(String latitude, String longitude, String radius, boolean useFallback);
}
