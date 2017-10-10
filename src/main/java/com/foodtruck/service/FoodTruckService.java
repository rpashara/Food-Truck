package com.foodtruck.service;

import com.foodtruck.bean.FoodTruckBean;
import rx.Observable;

import java.util.List;
import java.util.Optional;

/**
 * Created by rpashara on 10/9/2017.
 */
public interface FoodTruckService {

    Observable<Optional<List<FoodTruckBean>>> getNearFoodTrucksAsync(String latitude, String longitude);
}
