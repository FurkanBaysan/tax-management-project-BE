package com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "tax_logs") // -> This annotation tells Spring Data MongoDB that
                                    // this class is a MongoDB document.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaxLog {
    @Id
    private String id; // MongoDB generates a unique identifier for each document.

    private String productName;
    private double price;
    private double taxRate;
    private double taxAmount;
    private double totalPrice;
    private LocalDateTime timestamp; // -> Timestamp of the calculation
}
