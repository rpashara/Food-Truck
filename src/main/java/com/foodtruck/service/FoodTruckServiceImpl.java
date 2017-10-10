package com.foodtruck.service;

import com.foodtruck.bean.FoodTruckBean;
import com.foodtruck.repository.FoodTruckRepository;
import com.foodtruck.util.HelperUtils;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

/**
 * Created by rpashara on 10/9/2017.
 */

@Service
public class FoodTruckServiceImpl implements FoodTruckService {


    @Value("${small.radius}")
    private String smallRadius;

    @Value("${large.radius}")
    private String largeRadius;

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(FoodTruckServiceImpl.class);

    private static final HystrixCommandGroupKey hystrixCommandGroupKey = HystrixCommandGroupKey.Factory
            .asKey("FoodTruckServiceImpl");

    @Override
    public Observable<Optional<List<FoodTruckBean>>> getNearFoodTrucksAsync(String latitude, String longitude) {

        return new HystrixObservableCommand<Optional<List<FoodTruckBean>>>(
                HystrixObservableCommand.Setter.withGroupKey(
                        hystrixCommandGroupKey).andCommandKey(
                        HystrixCommandKey.Factory.asKey("createCustomerAsync"))) {

            @Override
            protected Observable<Optional<List<FoodTruckBean>>> construct() {
                return getNearFoodTrucks(smallRadius, false);
            }

            @Override
            protected Observable<Optional<List<FoodTruckBean>>> resumeWithFallback() {
                return getNearFoodTrucks(largeRadius, true);
            }

            protected Observable<Optional<List<FoodTruckBean>>> getNearFoodTrucks(String radius, boolean useFallBack) {
                List<FoodTruckBean> foodTruckBeanList = new ArrayList<>();
                return foodTruckRepository.getNearFoodTrucks(latitude, longitude, radius, useFallBack)
                    .map(foodTrucks -> {
                        foodTrucks.get().stream().forEach(foodTruck -> {
                            FoodTruckBean foodTruckBean = HelperUtils.convertFoodTruckToFoodTruckBean(foodTruck);
                            foodTruckBean.setDistance(HelperUtils.getDistance(Double.parseDouble(longitude), Double.parseDouble(latitude),
                                    Double.parseDouble(foodTruckBean.getLongitude()), Double.parseDouble(foodTruckBean.getLatitude())));
                            foodTruckBeanList.add(foodTruckBean);

                        });
                        return Optional.of(foodTruckBeanList);
                    });
            }

        }.toObservable();
    }
}
