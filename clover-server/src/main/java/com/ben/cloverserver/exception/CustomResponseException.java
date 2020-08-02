package com.ben.cloverserver.exception;

import com.ben.cloverserver.constant.ResponseCode;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomResponseException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(HttpRequest request) {
        System.out.println(request);
        return new ResponseEntity<>(new com.ben.cloverserver.response
                .ResponseEntity(ResponseCode.WRONG_DATA_FORMAT).toString(), HttpStatus.NOT_FOUND);
    }
}
