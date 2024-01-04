package com.sweaters.demo1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ItemsNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ItemsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ItemsNotFoundHandler(ItemsNotFoundException ex)
    {
        return ex.getMessage();
    }
}
