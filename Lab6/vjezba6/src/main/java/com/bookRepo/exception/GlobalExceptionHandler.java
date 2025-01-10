package com.bookRepo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiException> handleBookNotFoundException(BookNotFoundException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiException> handleInvalidRequestException(InvalidRequestException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
                );

        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);

    }
}
