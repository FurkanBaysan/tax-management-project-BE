package com.TaxCalculatorProject.TaxCalculatorBackEnd.business.concretes;

import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.abstracts.TaxService;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.TaxCalculationRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.Product;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.TaxLog;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.repositories.TaxLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TaxManager implements TaxService {

    private final TaxLogRepository taxLogRepository;


    @Autowired
    public TaxManager(TaxLogRepository taxLogRepository) { //DI
        this.taxLogRepository = taxLogRepository;
    }

    @Override
    public double calculateFinalPriceIncludingTax(ProductRequestDTO productRequestDTO) {

        double finalAmountOfProduct = productRequestDTO.getPrice() +
                (productRequestDTO.getPrice() * productRequestDTO.getTaxCalculationRequestDTO().getTaxRate());

        return finalAmountOfProduct;
    }

    @Override
    public double calculateTaxAmount(ProductRequestDTO productRequestDTO) {

        double taxAmount = productRequestDTO.getPrice() * productRequestDTO.getTaxCalculationRequestDTO().getTaxRate();

        return taxAmount;

    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Starts a new, independent transaction
    // -> To make sure that failures in saveTaxLog don’t impact
    // the main transaction in createProduct or updateProduct,
    // we can configure it to use a separate transaction.
    // This way, if saveTaxLog fails (e.g., due to a MongoDB error), the main product operation can still succeed.
    public TaxLog saveTaxLog(String productName, double price, double taxRate, double taxAmount, double totalPrice) {

        TaxLog taxLog = new TaxLog(null, productName, price, taxRate, taxAmount, totalPrice, LocalDateTime.now());
        return this.taxLogRepository.save(taxLog);

    }
}
