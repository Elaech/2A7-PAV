package com.PAV11.lab.errors;

import org.springframework.stereotype.Component;

@Component
public class MyError extends Error{
    public MyError(){
        super();
    }

    public MyError(String message){
        super(message);
    }
}
