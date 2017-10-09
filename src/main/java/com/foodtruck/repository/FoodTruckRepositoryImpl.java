package com.foodtruck.repository;

import com.foodtruck.bean.FoodTruck;
import com.foodtruck.exception.DataException;
import com.foodtruck.exception.DataNotFoundException;
import com.foodtruck.exception.RestOperationException;
import com.foodtruck.util.ObservableLogHelper;
import com.foodtruck.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FoodTruckRepositoryImpl implements FoodTruckRepository {

    @Autowired
   private RestTemplate restTemplate;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(FoodTruckRepositoryImpl.class);

    @Value("${externalserverpath}")
    private String externalServerPath;

    @Value("${location}")
    private String location;

    @Override
    public Observable<Optional<FoodTruck>> getNearFoodTrucks(String latitude, String longitude, String radius, boolean useFallback) {

        return Observable.create((Subscriber<? super Optional<FoodTruck>> subscriber) ->{
            try {
                if (!subscriber.isUnsubscribed()) {
                    final String url = externalServerPath + "(" + location + StringUtils.COMMA + latitude + StringUtils.COMMA + longitude + StringUtils.COMMA + radius + ")";
                    HttpHeaders requestHeaders = new HttpHeaders();
                    List<MediaType> mediaTypeList = new ArrayList<MediaType>();
                    mediaTypeList.add(MediaType.APPLICATION_JSON);
                    requestHeaders.setAccept(mediaTypeList);
                    requestHeaders.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestHeaders);
                    FoodTruck foodTruck = restTemplate.exchange(url, HttpMethod.GET, requestEntity,FoodTruck.class).getBody();
                    if (foodTruck != null) {
                        subscriber.onNext(Optional.of(foodTruck));
                    }
                }
            }catch (Exception ex){

                if(ex instanceof ResourceAccessException || ex instanceof HttpClientErrorException || ex instanceof HttpServerErrorException){
                    subscriber.onError(new RestOperationException("Error occurred during rest operations.", ex));
                }else{
                    subscriber.onError(ex);
                }
            } finally {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        }).doOnEach(ObservableLogHelper.log(LOGGER, "food truck data retrieved successfully."))
                .switchIfEmpty(Observable.error(new DataNotFoundException("No food trucks are available near given location.")))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}

