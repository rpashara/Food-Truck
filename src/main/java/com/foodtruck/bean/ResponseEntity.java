package com.foodtruck.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ResponseEntity<T> {

    @JsonProperty("status")
    private String status;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("data")
    private T data;

    @JsonProperty("errors")
    private List<ErrorJsonBean.ErrorBean> errors;
}
