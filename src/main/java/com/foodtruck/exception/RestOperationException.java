/**
 * 
 */
package com.foodtruck.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @author rpashara
 *
 */
public class RestOperationException extends HystrixBadRequestException {


	public RestOperationException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RestOperationException(String message, Throwable cause) {
		super(message, cause);
	}

}
