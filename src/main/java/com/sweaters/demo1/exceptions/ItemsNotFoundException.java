package com.sweaters.demo1.exceptions;

//Пробник ответа
public class ItemsNotFoundException extends RuntimeException {

    public ItemsNotFoundException(Long Id)
    {
        super("No items with "+ Id + "found.");
    }
}
