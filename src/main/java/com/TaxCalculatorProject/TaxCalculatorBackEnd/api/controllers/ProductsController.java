package com.TaxCalculatorProject.TaxCalculatorBackEnd.api.controllers;

import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.abstracts.ProductService;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.constants.Paths;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.ProductResponseDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.business.dtos.TaxCalculationRequestDTO;
import com.TaxCalculatorProject.TaxCalculatorBackEnd.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(Paths.apiPrefix + "products/")
public class ProductsController {


    private final ProductService productService; // "final" because -> Immutability of the Reference and Guaranteed Constructor Injection


    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("get-All-Products")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = this.productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);

    }


    //@PathVariable: For extracting values residing/embedded in the URL path, e.g., /products/{productId}.
    //@RequestParam: For extracting values from the URL’s query string, e.g., /products?productId=5.
    //@PathParam (JAX-RS API): Used to extract values from the URL path in JAX-RS-based web services (similar to @PathVariable in Spring).
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {

        Product product = this.productService.getProductById(productId);

        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestParam Long userId, @RequestBody ProductRequestDTO productRequestDTO) {

        ProductResponseDTO createdProduct = this.productService.createProduct(userId, productRequestDTO);

        return new ResponseEntity<ProductResponseDTO>(createdProduct, HttpStatus.CREATED);

        //return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);  ------> EQUIVALENT OF ABOVE !


        //return new ResponseEntity<>(createdProduct,HttpStatus.CREATED).getBody()

        // ----------> IT EXTRACTS THE BODY PART OF THE -> ResponseEntity object (representing HTTP Response Message)
        //                              and returns only the body of the HTTP Response Message


        //return new ResponseEntity<ProductResponseDTO>(createdProduct,HttpStatus.CREATED).getStatusCode()

        // ------------> IT EXTRACTS THE HTTP STATUS CODE PART OF THE ResponseEntity object (representing HTTP Response Message)
        // and return only the HTTP status code of the HTTP Response Message

    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestParam Long userId, @PathVariable Long productId, @RequestBody ProductRequestDTO productRequestDTO, TaxCalculationRequestDTO taxCalculationRequestDTO) {

        ProductResponseDTO updatedProduct = this.productService.updateProduct(userId, productId, productRequestDTO, taxCalculationRequestDTO);

        return new ResponseEntity<ProductResponseDTO>(updatedProduct, HttpStatus.OK);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId, @RequestParam Long userId) {

        this.productService.deleteProduct(productId, userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
