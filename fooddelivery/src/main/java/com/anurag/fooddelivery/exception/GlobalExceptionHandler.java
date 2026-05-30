package com.anurag.fooddelivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String,Object>>
    handleResponseStatusException(
            ResponseStatusException ex){

        Map<String,Object> error =
                new HashMap<>();

        error.put(
                "status",
                ex.getStatusCode().value()
        );

        error.put(
                "message",
                ex.getReason()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.valueOf(
                        ex.getStatusCode().value()
                )
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>
    handleValidationException(
            MethodArgumentNotValidException ex){

        Map<String,String> errors =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );

                });

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST
        );
    }
}