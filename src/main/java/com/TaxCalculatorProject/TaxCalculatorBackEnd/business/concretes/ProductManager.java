package com.TaxCalculatorProject.TaxCalculatorBackEnd.business.concretes;

import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.abstracts.ProductService;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.abstracts.TaxService;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductResponseDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.TaxCalculationRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.core.utils.exceptions.ProductNotFoundException;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.core.utils.exceptions.UnauthorizedException;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.Product;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final TaxService taxService;

    @Autowired
    public ProductManager(ProductRepository productRepository, TaxService taxService) {
        this.productRepository = productRepository;
        this.taxService = taxService;
    }


    @Override
    @Transactional
    public List<Product> getAllProducts() {

        List<Product> products = this.productRepository.findAll();
        return products;

    }

    @Override
    @Transactional
    public Product getProductById(Long productId) {
//        Optional<Product> product = this.productRepository.findById(productId);
//        if (product.isPresent()) {
//            return product.get();
//        } else {
//            throw new ProductNotFoundException("Product with ID " + id + " not found");
//        }

        return this.productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product with id " + productId + "not found"));

    }


    @Override
    @Transactional
    public ProductResponseDTO createProduct(Long userId, ProductRequestDTO productRequestDTO) {


        Product product = new Product();

        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setUserId(userId);

        Product savedProduct = this.productRepository.save(product);
        //We created and saved a new Product to DB.


        //Now, let's map this Product attributes to ProductResponseDTO, which we expose it from our API-layer.
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(savedProduct.getId());
        productResponseDTO.setName(savedProduct.getName());
        productResponseDTO.setPrice(savedProduct.getPrice());

        //lastly, we should set the finalPriceIncludingTax attribute of productResponseDTO
        // with the data which is returning from calculateFinalPriceIncludingTax method of
        // TaxManager object
        double finalPriceIncludingTax = this.taxService.calculateFinalPriceIncludingTax(productRequestDTO);
        productResponseDTO.setFinalPriceIncludingTax(finalPriceIncludingTax);


        //Finally, let's log this Tax-related attribute values to the related MongoDB document
        this.taxService.saveTaxLog(
                savedProduct.getName(),
                savedProduct.getPrice(),
                productRequestDTO.getTaxCalculationRequestDTO().getTaxRate(),
                this.taxService.calculateTaxAmount(productRequestDTO),
                finalPriceIncludingTax
        );


        return productResponseDTO;

    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long userId, Long productId, ProductRequestDTO productRequestDTO, TaxCalculationRequestDTO taxCalculationRequestDTO) {

        // Validate inputs/parameters
        if (productRequestDTO == null || taxCalculationRequestDTO == null) {
            throw new IllegalArgumentException("Product and tax information must not be null");
        }

        Product product = this.productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product with id " + productId + "not found"));


        if (!product.getUserId().equals(userId)) {
            throw new UnauthorizedException("Unauthorized User");
        }

        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());

        Product updatedProduct = this.productRepository.save(product);

        // Now, after the update operation, we will map the necessary Product attributes
        // to the ProductResponseDTO to return ProductResponseDTO to the client from our API-layer.

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setName(updatedProduct.getName());
        productResponseDTO.setPrice(updatedProduct.getPrice());

        // Because of the original price of the related product change with the given value/data which comes from client,
        // the final price of the related product including tax will be changed. So, we should calculate it again
        // and return to the client using my relevant Controller object i.e ProductsController

        double finalPriceIncludingTax = this.taxService.calculateFinalPriceIncludingTax(productRequestDTO);
        productResponseDTO.setFinalPriceIncludingTax(finalPriceIncludingTax);


        //After updating Product attributes and return the Response body to the client,
        // Finally, let's log this Tax-related attribute values to the related MongoDB document
        this.taxService.saveTaxLog(
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                taxCalculationRequestDTO.getTaxRate(),
                this.taxService.calculateTaxAmount(productRequestDTO),
                finalPriceIncludingTax
        );


        return productResponseDTO;

    }

    @Override
    @Transactional
    public void deleteProduct(Long productId, Long userId) {

        Product product = this.productRepository.findById(productId).
                orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + "not found"));


        if (!product.getUserId().equals(userId)) {
            throw new UnauthorizedException("Unauthorized");
        }


        this.productRepository.delete(product);

    }
}
