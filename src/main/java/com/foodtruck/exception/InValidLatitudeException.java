package com.foodtruck.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

public class InValidLatitudeException extends HystrixBadRequestException {

    private static final long serialVersionUID = 1L;

    public InValidLatitudeException(String message) {
        super(message);
    }

    public InValidLatitudeException(String message, Throwable cause) {
        super(message, cause);
    }
}
