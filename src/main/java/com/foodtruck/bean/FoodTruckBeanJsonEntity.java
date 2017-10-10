package com.foodtruck.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by rpashara on 10/9/2017.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class FoodTruckBeanJsonEntity {

    @JsonProperty("foodtruckBeans")
    List<FoodTruckBean> foodTruckBeanList;

    @JsonProperty("errors")
    private List<ErrorJsonBean.ErrorBean> errors;
}
