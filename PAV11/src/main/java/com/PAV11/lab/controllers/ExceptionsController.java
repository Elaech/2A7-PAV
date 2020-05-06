package com.PAV11.lab.controllers;

import com.PAV11.lab.errors.MyError;
import com.PAV11.lab.exceptions.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<MyError> handleGenericException(MyException e){
        MyError error = new MyError(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

