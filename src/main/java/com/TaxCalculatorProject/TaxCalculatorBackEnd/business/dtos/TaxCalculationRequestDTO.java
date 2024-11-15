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
public class TaxCalculationRequestDTO {
//    @NotNull(message = "Product price cannot be null")
//    private double price;

    @NotNull(message = "Tax rate cannot be null")
    private double taxRate;
}
