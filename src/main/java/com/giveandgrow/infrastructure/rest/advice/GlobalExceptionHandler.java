package com.giveandgrow.infrastructure.rest.advice;

import com.giveandgrow.infrastructure.rest.response.Response;
import com.giveandgrow.shared.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ControllerGiveAndGrowException.class,
            DomainGiveAndGrowException.class,
            ServiceGiveAndGrowException.class,
            InfrastructureGiveAndGrowException.class,
            SharedGiveAndGrowException.class
    })
    public ResponseEntity<Response<Object>> handleCustomExceptions(RuntimeException ex){
        Response<Object> response = Response.error(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleGeneralException(Exception ex){
        Response<Object> response = Response.error(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred.");
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response<Object>> handleBadCredentials(BadCredentialsException ex) {
        Response<Object> response = Response.error(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }


}
