package com.foodtruck.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @author rpashara
 *
 */
public class DataException extends HystrixBadRequestException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
