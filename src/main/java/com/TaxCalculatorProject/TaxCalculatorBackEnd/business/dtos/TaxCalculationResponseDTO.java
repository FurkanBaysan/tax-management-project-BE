package com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaxCalculationResponseDTO {

    private double price;  // Original price
    private double taxRate;  // Tax rate
    private double taxAmount;  // Calculated tax amount
    private double totalPrice;  // Total price (price + tax)

}
