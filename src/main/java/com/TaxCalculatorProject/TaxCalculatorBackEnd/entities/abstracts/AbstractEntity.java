package com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.abstracts;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity implements Serializable {

    protected LocalDateTime createdOn;
    protected LocalDateTime updatedOn;

}
