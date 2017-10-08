package com.foodtruck.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionController {

    private static final transient Logger LOGGER = LoggerFactory
            .getLogger(GlobalExceptionController.class);
}
