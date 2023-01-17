package com.enoca.backendChallenge2.exceptions;

import com.enoca.backendChallenge2.results.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResult> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        ErrorResult error = new ErrorResult("Error: " + ex.getMessage());
        error.setErrorCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResult>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResult> handleOrderNotFoundException(OrderNotFoundException ex) {
        ErrorResult error = new ErrorResult("Error: " + ex.getMessage());
        error.setErrorCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResult>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleException(Exception ex) {
        ErrorResult error = new ErrorResult("Error: " + ex.getMessage());
        error.setErrorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResult>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
