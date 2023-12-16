/*
 * (C) Copyright Spacebel, 2022
 *
 * Project: Spacebel Product
 * Product: Timeline
 */
package com.telemis.africanbowlinggame.api;

import com.telemis.africanbowlinggame.model.GameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameException.class)
    public final ResponseEntity<String> handleServerError(GameException e) {
        logger.error("", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleServerError(Exception e) {
        logger.error("Internal server error", e);
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
