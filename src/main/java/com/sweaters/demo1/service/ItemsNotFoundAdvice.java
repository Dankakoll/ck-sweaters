package com.sweaters.demo1.service;

import com.sweaters.demo1.exceptions.ItemsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//Если не найден по Id, то выдавать ошибку
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
