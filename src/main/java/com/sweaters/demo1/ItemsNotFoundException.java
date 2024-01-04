package com.sweaters.demo1;

class ItemsNotFoundException extends RuntimeException {

    ItemsNotFoundException(Long Id)
    {
        super("No items with "+ Id + "found.");
    }
}
