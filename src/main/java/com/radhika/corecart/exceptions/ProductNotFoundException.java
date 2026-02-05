package com.radhika.corecart.exceptions;

public class ProductNotFoundException  extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
