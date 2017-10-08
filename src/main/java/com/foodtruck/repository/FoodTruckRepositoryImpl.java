package com.foodtruck.repository;

import com.foodtruck.bean.FoodTruck;
import com.foodtruck.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

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
    public FoodTruck getNearFoodTrucks(String latitude, String longitude, String radius) {
        return Observable.create((Subscriber<? super Optinal<FoodTruck>> subscrber) ->{
            try {
                final static String  url = externalServerPath + "(" + location +  StringUtils.COMMA + latitude + StringUtils.COMMA + longitude + StringUtils.COMMA+ radius + ")";
                FoodTruck foodTruck = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<Object>(), FoodTruck.class);
            }
        });

       /* try{
            HttpEntity<Object> requestEntity = new HttpEntity<>();
            FoodTruck foodTruck = restTemplate.exchange( externalServerPath + "(" + location +  "," + latitude +"," + longitude + ","+ radius + ")",
                    HttpMethod.GET,requestEntity,FoodTruck.class).getBody();
            if (foodTruck != null) {

            }
        }
*/

        return null;

     /*   return Observable.create((Subscriber<? super Optional<SuccessMessage>> subscriber) -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    final String adjustmentUrl = csApiEndPoints.getCsapiServerPath() + csApiEndPoints.getAccounts() + customerKey + csApiEndPoints.getAdjustment();


                    AdjustmentJsonEntity adjustmentJsonEntity = new AdjustmentJsonEntity();
                    adjustmentJsonEntity.setAdjustment(adjustment);

                    HttpEntity<AdjustmentJsonEntity> requestEntity = new HttpEntity<AdjustmentJsonEntity>(adjustmentJsonEntity, HttpUtils.getRestRequestHttpHeader(authorization));
                    ParameterizedTypeReference<GenericSuccessEntity> responseType = new ParameterizedTypeReference<GenericSuccessEntity>() {};
                    GenericSuccessEntity genericSuccessEntity  = restTemplate.exchange(adjustmentUrl, HttpMethod.POST, requestEntity, responseType).getBody();

                    if(genericSuccessEntity != null && genericSuccessEntity.getSuccessMessage() != null){
                        subscriber.onNext(Optional.of(genericSuccessEntity.getSuccessMessage()));
                    }
                }
            } catch (Exception ex) {
                LOGGER.debug("Error while make adjustment.",ex);
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
        }).doOnEach(ObservableLogHelper.log(LOGGER, "adjustment for  " + customerKey + " is done sucessfully " ))
                .switchIfEmpty(Observable.error(new DataException("Input data is invalid.")))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }*/
    }
}
