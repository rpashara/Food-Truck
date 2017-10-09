/**
 * 
 */
package com.foodtruck.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @author rpashara
 *
 */
public class DataNotFoundException extends HystrixBadRequestException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5532404107467530215L;

	public DataNotFoundException(String message) {
		super(message);
	}
	
	public DataNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}
	
}
