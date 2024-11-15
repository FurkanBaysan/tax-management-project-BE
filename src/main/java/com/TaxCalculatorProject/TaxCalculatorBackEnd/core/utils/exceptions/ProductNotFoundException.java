package com.TaxCalculatorProject.TaxCalculatorBackEnd.core.utils.exceptions;

public class ProductNotFoundException extends RuntimeException {

    // constructor that accepts a custom message
    public ProductNotFoundException(String message) {
        super(message);
    }
}
