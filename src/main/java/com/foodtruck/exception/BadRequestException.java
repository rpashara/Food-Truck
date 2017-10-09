package com.foodtruck.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @author rpashara
 *
 */
public class BadRequestException extends HystrixBadRequestException {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
