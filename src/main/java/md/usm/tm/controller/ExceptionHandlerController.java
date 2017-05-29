package md.usm.tm.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pcovaliov on 11/11/2016.
 */

@ControllerAdvice
public class ExceptionHandlerController {

    public static final String DEFAULT_ERROR_VIEW = "error";

    private static final Logger logger = RootLogger.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        logger.error(e);
        return "error";
    }
}
