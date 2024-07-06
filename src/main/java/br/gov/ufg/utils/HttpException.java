package br.gov.ufg.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpException extends Throwable {

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<Object> handleException(Exception e, HttpStatus status) {
        System.err.println(e);
        return new ResponseEntity<Object>("Message: " + e.getMessage(), status);
    }

    public static ResponseEntity<Object> handleException(String str, HttpStatus status) {
        return new ResponseEntity<Object>("Message: " + str, status);
    }
}