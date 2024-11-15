package com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes;

import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.abstracts.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String name;

    private Double price;

    @Column(name = "user_id")
    private Long userId; // Used to link products to the user

}
