package com.foodtruck.repository;

import com.foodtruck.bean.FoodTruck;
import rx.Observable;

import java.util.List;
import java.util.Optional;

public interface FoodTruckRepository {



    Observable<Optional<List<FoodTruck>>> getNearFoodTrucks(String latitude, String longitude, String radius, boolean useFallBack);
}
