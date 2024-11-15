package com.TaxCalculatorProject.TaxCalculatorBackEnd.repositories;

import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.TaxLog;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaxLogRepository extends MongoRepository<TaxLog, String> {

    public List<TaxLog> findByProductName(String productName);

    public List<TaxLog> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    public List<TaxLog> findByTaxRate(double taxRate);

    public List<TaxLog> findByPriceGreaterThan(double price);

}
