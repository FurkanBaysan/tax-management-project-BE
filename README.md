# Tax Calculator Backend API

## Description

This project is a **Spring Boot** application that serves as a backend for a **Tax Calculator** service. It provides a set of RESTful APIs that allow clients to:

- **Create, modify, and delete products**.
- **View all products** in the system.
- **Calculate tax** for products based on predefined tax rules.

This backend application supports the following features:

- **CRUD** operations for products.
- Tax calculation functionality.
- Logs tax-related information in a MongoDB collection (`TaxLog`).

The project uses **PostgreSQL** for storing product data and **MongoDB** for storing tax calculation logs.

---

## Technologies Used

- **Java**: 17+
- **Spring Boot**: 3.x
- **PostgreSQL**: For persistent storage of product data
- **MongoDB**: For logging tax calculation data
- **Spring Data JPA**: For interacting with PostgreSQL
- **Spring Data MongoDB**: For interacting with MongoDB
- **Swagger/OpenAPI**: For documenting the API endpoints

---

## Features

### 1. **Product Management**
   - **Create a product**: Allows a user to create a new product.
   - **Modify own product**: A user can modify their own products, but cannot modify other users' products.
   - **Delete own product**: A user can delete their own products.
   - **View all products**: A user can view all products in the system.

### 2. **Tax Calculation**
   - **Calculate tax**: Users can calculate the tax of a product based on predefined tax rates.

---

Error Handling
The application provides the following custom exceptions:

**EntityNotFoundException: Thrown when a requested entity (product) is not found.
**GlobalExceptionHandler: Handles and returns meaningful error responses for all exceptions.

---
## API Documentation

This project follows RESTful API design. Here is a summary of the API endpoints:

### 1. **Product API**
   - `POST /products`: Create a new product.
   - `GET /products`: View all products.
   - `PUT /products/{id}`: Update the product.
   - `DELETE /products/{id}`: Delete a product.
   
### 2. **Tax API**
   - `POST /tax/calculateFinalPriceIncludingTax`: Calculate tax for a product.
   - `POST /tax/calculateTaxAmount` : Calculating the taxAmount based on taxRate.



