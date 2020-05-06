package com.PAV11.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyException extends RuntimeException {
    public MyException() {
        super();
    }
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
    public MyException(String message) {
        super(message);
    }
    public MyException(Throwable cause) {
        super(cause);
    }
}