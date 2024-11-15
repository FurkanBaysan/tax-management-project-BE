package com.TaxCalculatorProject.TaxCalculatorBackEnd.business.abstracts;

import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductResponseDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.TaxCalculationRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProductById(Long productId);

    public ProductResponseDTO createProduct(Long userId, ProductRequestDTO productRequestDTO);

    public ProductResponseDTO updateProduct(Long userId, Long productId, ProductRequestDTO productRequestDTO,TaxCalculationRequestDTO taxCalculationRequestDTO);

    public void deleteProduct(Long productId, Long userId); //TODO : adding a central messaging mechanism maybe
}
