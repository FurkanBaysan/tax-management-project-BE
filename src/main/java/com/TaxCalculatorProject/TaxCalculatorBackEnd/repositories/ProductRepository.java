package com.TaxCalculatorProject.TaxCalculatorBackEnd.repositories;

import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products by user ID (for user-based product access)
    public List<Product> findByUserId(Long id);

    // Find products within a specific price range (for filtering)
    public List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Find products by name (useful for search functionality)
    public List<Product> findByNameContaining(String name);

    // Find all products sorted by price in ascending order (for sorting by price)
    public List<Product> findAllByOrderByPriceAsc();

    // Find all products sorted by price in descending order (for sorting by price)
    public List<Product> findAllByOrderByPriceDesc();

}
