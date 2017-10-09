package com.foodtruck.service;

import com.foodtruck.bean.FoodTruckBean;
import com.foodtruck.repository.FoodTruckRepository;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Optional;

@Service
public class FoodTruckServiceImpl implements FoodTruckService{

    private static final HystrixCommandGroupKey hystrixCommandGroupKey = HystrixCommandGroupKey.Factory
            .asKey("FoodTruckServiceImpl");
    private static final transient Logger log = LoggerFactory
            .getLogger(FoodTruckServiceImpl.class);

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    @Value("${small.radius")
    private String smallRadius;

    @Value("${large.radius}")
    private  String largeRadius;

    @Override
    public Observable<Optional<FoodTruckBean>> getNearFoodTrucksAsync(String latitude, String longitude){

        return new HystrixObservableCommand<Optional<FoodTruckBean>>(
                HystrixObservableCommand.Setter.withGroupKey(
                        hystrixCommandGroupKey).andCommandKey(
                        HystrixCommandKey.Factory.asKey("getNearFoodTrucksAsync"))) {

            @Override
            protected Observable<Optional<FoodTruckBean>> construct() {
                log.debug("Calling construct method of hystrix command:AdjustmentService.getNearFoodTrucksAsync");
                return getNearFoodTrucksWithRadius(smallRadius,false);
            }

            @Override
            protected Observable<Optional<FoodTruckBean>> resumeWithFallback() {
                log.debug("Calling resumeWithFallback method of hystrix command:AdjustmentService.getNearFoodTrucksAsync");
                return getNearFoodTrucksWithRadius(largeRadius,true);
            }

            private Observable<Optional<FoodTruckBean>> getNearFoodTrucksWithRadius(String radius, boolean useFallBack) {

                return  foodTruckRepository.getNearFoodTrucks(latitude,longitude,radius,useFallBack)
                        .map()
            }


        }.toObservable();

/*
        return new HystrixObservableCommand<Optional<List<AdjustmentAccountCode>>>(
                HystrixObservableCommand.Setter.withGroupKey(
                        hystrixCommandGroupKey).andCommandKey(
                        HystrixCommandKey.Factory.asKey("getAccountCodeListForAdjustmentAsync"))) {

            @Override
            protected Observable<Optional<List<AdjustmentAccountCode>>> construct() {
                log.debug("Calling construct method of hystrix command:AdjustmentService.getAccountCodeListForAdjustmentAsync");
                return getAccountCodeListForAdjustment(false);
            }

            @Override
            protected Observable<Optional<List<AdjustmentAccountCode>>> resumeWithFallback() {
                log.debug("Calling resumeWithFallback method of hystrix command:AdjustmentService.getAccountCodeListForAdjustmentAsync");
                return getAccountCodeListForAdjustment(true);
            }

            protected Observable<Optional<List<AdjustmentAccountCode>>> getAccountCodeListForAdjustment(Boolean fallback){

                return Observable.zip(adjustmentRepository.getJournalItemCountByAccountCode(accountId,lateFeeAccountCode,fallback)
                        ,adjustmentRepository.getJournalItemCountByAccountCode(accountId,lateFeeAdjustmentAccountCode,fallback)
                        ,accountRepository.getAccount(accountId,fallback)
                        ,(lateFeeCodeOp, lateFeeAdjustmentCodeOp,accountOp) ->  {
                            List<String> accountCodesList = new ArrayList<String>();
                            List<String> lateFeeAdjustmentAllowedCountryList = new ArrayList<>();
                            for(String countryCode : lateFeeAdjustmentAllowedCountries.split(",")){
                                lateFeeAdjustmentAllowedCountryList.add(countryCode);
                            }
                            accountCodesList.add(billingAdjustmentAccountCode);
                            if(lateFeeCodeOp.get().compareTo(lateFeeAdjustmentCodeOp.get()) != 0
                                    && !lateFeeAdjustmentAllowedCountryList.isEmpty()
                                    && lateFeeAdjustmentAllowedCountryList.contains(accountOp.get().getAccountProfile().getContactAddress().getCountryISOCode())){
                                accountCodesList.add(lateFeeAdjustmentAccountCode);
                            }
                            return accountCodesList;})
                        .flatMap(accountCodeList -> Observable.zip(adjustmentRepository.getAccountCodeDetailsForAdjustment(accountCodeList, fallback)
                                ,adjustmentRepository.getJournalLatestItemAmountByAccountCode(accountId,lateFeeAccountCode,fallback)
                                ,((adjustmentAccountCodes, amount) -> {
                                    for(AdjustmentAccountCode adjustmentAccountCode : adjustmentAccountCodes.get()){
                                        if(StringUtils.equals(adjustmentAccountCode.getCode(),lateFeeAdjustmentAccountCode)){
                                            adjustmentAccountCode.setDefaultAmount(amount.get());
                                        }
                                    }
                                    return  adjustmentAccountCodes;
                                })));
            }
        }.toObservable();*/
    }
}
