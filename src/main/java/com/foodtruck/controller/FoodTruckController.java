package com.foodtruck.controller;

import com.foodtruck.bean.ErrorJsonBean;
import com.foodtruck.bean.FoodTruckBeanJsonEntity;
import com.foodtruck.exception.DataNotFoundException;
import com.foodtruck.exception.InValidLatitudeException;
import com.foodtruck.exception.InValidLongitudeException;
import com.foodtruck.exception.RestOperationException;
import com.foodtruck.service.FoodTruckService;
import com.foodtruck.util.ErrorConstants;
import com.foodtruck.util.HelperUtils;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.annotation.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpashara on 10/9/2017.
 */

@Controller
public class FoodTruckController {

    private static final transient Logger LOGGER = LoggerFactory
            .getLogger(FoodTruckController.class);
    @Autowired
    private FoodTruckService foodTruckService;


    /**
     *  This method provides list of food trucks near your given location.
     * @param latitude, latitude co-ordinate of location
     * @param longitude, longitude co-ordinate of location
     * @param httpResponse
     * @param httpRequest
     * @return FoodTruckBeanJsonEntity
     * @throws ResourceAccessException
     * @throws DataNotFoundException
     * @throws InValidLatitudeException
     * @throws InValidLongitudeException
     * @throws Exception
     */
    @RequestMapping(value = "/foodTrucks", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<FoodTruckBeanJsonEntity> getFoodTrucks(@RequestParam(required = true) String latitude, @RequestParam(required = true) String longitude,
                                                          HttpServletResponse httpResponse, HttpServletRequest httpRequest) throws ResourceAccessException,DataNotFoundException,InValidLatitudeException, InValidLongitudeException,Exception {

        FoodTruckBeanJsonEntity foodTruckBeanJsonEntity = new FoodTruckBeanJsonEntity();
        ErrorJsonBean.ErrorBean errorBean = new ErrorJsonBean.ErrorBean();

        if(!StringUtils.isNumeric(latitude) ){
            throw new InValidLatitudeException("Latitude must be numeric value.");
        }
        if(!StringUtils.isNumeric(longitude) ){
            throw new InValidLongitudeException("Longitude must be numeric value.");
        }
        foodTruckService.getNearFoodTrucksAsync(latitude, longitude).subscribe(resultOp -> {
            foodTruckBeanJsonEntity.setFoodTruckBeanList(resultOp.get());


        }, error -> {
            if (error instanceof RestOperationException) {
                if (error.getCause() instanceof ResourceAccessException) {
                    throw new RestOperationException("Error while fetching food truck list.");
                } else if (error.getCause() instanceof HttpServerErrorException) {
                    throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while fetching food truck list. Error occurred in external API .");

                }
            } else if (error instanceof DataNotFoundException) {
                LOGGER.debug("Error while fetching countries. {}", error.getMessage(), error);
                errorBean.setErrorCode("403");
                errorBean.setDeveloperMessage("Country isn't available in Database");
                errorBean.setUserMessage("Country isn't available");
                List errorList = new ArrayList<ErrorJsonBean.ErrorBean>();
                errorList.add(errorBean);
                foodTruckBeanJsonEntity.setErrors(errorList);

                throw new DataNotFoundException("");
//
            } else {
                LOGGER.debug("Error while fetching countries. Unknown error occurred", error);
//                throw new Exception("");

            }
        });
        return new ResponseEntity<FoodTruckBeanJsonEntity>(foodTruckBeanJsonEntity, HttpStatus.OK);

    }


}
