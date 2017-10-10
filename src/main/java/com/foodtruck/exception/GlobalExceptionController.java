package com.foodtruck.exception;

import com.foodtruck.bean.ErrorJsonBean;
import com.foodtruck.util.ErrorConstants;
import com.foodtruck.util.HelperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;

import javax.servlet.http.HttpServletResponse;

/**
 * Handles each and every Exception that throws from controller.
 */
@ControllerAdvice
public class GlobalExceptionController {

    private static final transient Logger LOGGER = LoggerFactory
            .getLogger(GlobalExceptionController.class);


    @ExceptionHandler(value=ResourceAccessException.class)
    public @ResponseBody
    ErrorJsonBean handleResourceAccessException(HttpServletResponse httpResponse, ResourceAccessException accessExcp){
        LOGGER.error("Error while performing rest operation. Resource is not accessible.",accessExcp);
        final ErrorJsonBean errorJSONBean = new ErrorJsonBean();
        errorJSONBean.setErrors(HelperUtils.getErrorBeans(ErrorConstants.API_ACCESS_NOT_ALLOWED.name(),
                accessExcp.getMessage(),"Service is temporarily unavailable."));
        httpResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        httpResponse.setContentType("application/json");
        return errorJSONBean;
    }

    @ExceptionHandler(value=DataNotFoundException.class)
    public @ResponseBody
    ErrorJsonBean handleDataNotFoundException(HttpServletResponse httpResponse, DataNotFoundException accessExcp){
        LOGGER.error("Error while performing rest operation. Data is not available for given location.",accessExcp);
        final ErrorJsonBean errorJSONBean = new ErrorJsonBean();
        errorJSONBean.setErrors(HelperUtils.getErrorBeans(ErrorConstants.NO_DATA_FOUND.name(),
                accessExcp.getMessage(),"Data is not available for given location. try with different location."));
        httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        httpResponse.setContentType("application/json");
        return errorJSONBean;
    }
    @ExceptionHandler(value=Exception.class)
    public @ResponseBody
    ErrorJsonBean handleException(HttpServletResponse httpResponse, Exception accessExcp){
        LOGGER.error("Error while performing rest operation.",accessExcp);
        final ErrorJsonBean errorJSONBean = new ErrorJsonBean();
        errorJSONBean.setErrors(HelperUtils.getErrorBeans(ErrorConstants.INTERNAL_SERVER_ERROR.name(),
                accessExcp.getMessage(),"Service is temporarily unavailable."));
        httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        httpResponse.setContentType("application/json");
        return errorJSONBean;
    }

    @ExceptionHandler(value=InValidLongitudeException.class)
    public @ResponseBody
    ErrorJsonBean handleInValidLongitudeExceptionException(HttpServletResponse httpResponse, InValidLongitudeException accessExcp){
        LOGGER.error("Longitude value is invalid.",accessExcp);
        final ErrorJsonBean errorJSONBean = new ErrorJsonBean();
        errorJSONBean.setErrors(HelperUtils.getErrorBeans(ErrorConstants.INVALID_LONGITUDE.name(),
                accessExcp.getMessage(),"Longitude value is invalid.Enter a valid value."));
        httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        httpResponse.setContentType("application/json");
        return errorJSONBean;
    }

    @ExceptionHandler(value=InValidLatitudeException.class)
    public @ResponseBody
    ErrorJsonBean handleInValidLatitudeExceptionException(HttpServletResponse httpResponse, InValidLatitudeException accessExcp){
        LOGGER.error("Latitude value is invalid.",accessExcp);
        final ErrorJsonBean errorJSONBean = new ErrorJsonBean();
        errorJSONBean.setErrors(HelperUtils.getErrorBeans(ErrorConstants.INTERNAL_SERVER_ERROR.name(),
                accessExcp.getMessage(),"Latitude value is invalid.Enter a valid value."));
        httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        httpResponse.setContentType("application/json");
        return errorJSONBean;
    }
}
