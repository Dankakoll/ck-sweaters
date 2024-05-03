package com.sweaters.demo1.exceptions;

public class SellersNotFoundException extends RuntimeException {
    public SellersNotFoundException (Long Id)
    {
        super("Sellers with this" + Id + " not found.");
    }

}
