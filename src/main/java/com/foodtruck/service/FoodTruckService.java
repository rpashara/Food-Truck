package com.foodtruck.service;

import com.foodtruck.bean.FoodTruckBean;
import rx.Observable;

import java.util.Optional;

public interface FoodTruckService {

    Observable<Optional<FoodTruckBean>> getNearFoodTrucks(String latitude, String longitude);
}
