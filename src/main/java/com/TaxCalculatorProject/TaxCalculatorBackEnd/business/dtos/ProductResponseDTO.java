package com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private double price;
    private double finalPriceIncludingTax;
}
