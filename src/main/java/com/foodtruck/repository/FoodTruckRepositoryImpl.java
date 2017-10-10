package com.foodtruck.repository;

import com.foodtruck.bean.FoodTruck;
import com.foodtruck.bean.FoodTruckJsonEntity;
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

   private RestTemplate restTemplate;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(FoodTruckRepositoryImpl.class);

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Value("${externalserverpath}")
    private String externalServerPath;

    @Value("${location}")
    private String location;

    @Override
    public  Observable<Optional<List<FoodTruck>>> getNearFoodTrucks(String latitude, String longitude, String radius, boolean useFallBack) {

        return Observable.create((Subscriber<? super Optional<List<FoodTruck>>> subscriber) -> {
            try {
                if (!subscriber.isUnsubscribed()) {

                    HttpHeaders requestHeaders = new HttpHeaders();
                    List<MediaType> mediaTypeList = new ArrayList<MediaType>();
                    mediaTypeList.add(MediaType.APPLICATION_JSON);
                    requestHeaders.setAccept(mediaTypeList);
                    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestHeaders);

                    FoodTruckJsonEntity foodTruckJsonEntity = restTemplate.exchange(externalServerPath + "(" + location + "," + latitude + "," + longitude + "," + radius + ")", HttpMethod.GET, requestEntity, FoodTruckJsonEntity.class).getBody();
                    if (foodTruckJsonEntity != null && foodTruckJsonEntity.getFoodTrucks() != null) {
                        subscriber.onNext(Optional.of(foodTruckJsonEntity.getFoodTrucks()));
                    }
                }
            } catch (Exception ex) {
                LOGGER.debug("Error while retrieving food truck list.", ex);
                if (ex instanceof ResourceAccessException || ex instanceof HttpClientErrorException || ex instanceof HttpServerErrorException) {
                    subscriber.onError(new RestOperationException("Error occurred during rest operations.", ex));
                } else {
                    subscriber.onError(ex);
                }
            } finally {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }


        }).doOnEach(ObservableLogHelper.log(LOGGER, "retrieved food truck list."))
                .switchIfEmpty(Observable.error(new DataNotFoundException("No data retrieved.")))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());

    }
}
