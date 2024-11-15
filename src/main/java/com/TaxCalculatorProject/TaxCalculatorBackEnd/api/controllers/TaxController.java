package com.TaxCalculatorProject.TaxCalculatorBackEnd.api.controllers;


import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.abstracts.TaxService;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.constants.Paths;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Paths.apiPrefix + "tax/")
public class TaxController {

    private final TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) { //DI
        this.taxService = taxService;
    }

    @PostMapping("/calculate-final-price")
    public ResponseEntity<Double> calculateFinalPriceIncludingTax(@RequestBody ProductRequestDTO productRequestDTO) {

        double finalPriceIncludingTax = this.taxService.calculateFinalPriceIncludingTax(productRequestDTO);

        return new ResponseEntity<Double>(finalPriceIncludingTax, HttpStatus.OK);

    }

    @PostMapping("/calculate-tax-Amount")
    public ResponseEntity<Double> calculateTaxAmount(@RequestBody ProductRequestDTO productRequestDTO) {

        double taxAmount = this.taxService.calculateTaxAmount(productRequestDTO);

        return new ResponseEntity<Double>(taxAmount, HttpStatus.OK);

    }


}
