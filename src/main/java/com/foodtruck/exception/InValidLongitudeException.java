package com.foodtruck.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @author rpashara
 *
 */
public class InValidLongitudeException extends HystrixBadRequestException{

    private static final long serialVersionUID = 1L;

    public InValidLongitudeException(String message) {
        super(message);
    }

    public InValidLongitudeException(String message, Throwable cause) {
        super(message, cause);
    }
}
