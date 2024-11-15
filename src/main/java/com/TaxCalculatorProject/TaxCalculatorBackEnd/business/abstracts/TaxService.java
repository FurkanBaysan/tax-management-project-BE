package com.TaxCalculatorProject.TaxCalculatorBackEnd.business.abstracts;

import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.TaxCalculationRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.Product;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.TaxLog;

public interface TaxService {

    public double calculateFinalPriceIncludingTax(ProductRequestDTO productRequestDTO);
    public double calculateTaxAmount(ProductRequestDTO productRequestDTO);

    public TaxLog saveTaxLog(String productName, double price, double taxRate, double taxAmount, double totalPrice); //TODO : changeable..
}
