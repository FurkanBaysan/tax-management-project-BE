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
public class ProductRequestDTO {
    @NotNull(message = "Product name cannot be null")
    private String name;

    @NotNull(message = "Price cannot be null")
    private double price;

    TaxCalculationRequestDTO taxCalculationRequestDTO;
}
