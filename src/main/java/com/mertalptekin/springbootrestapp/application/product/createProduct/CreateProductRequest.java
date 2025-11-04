package com.mertalptekin.springbootrestapp.application.product.createProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mertalptekin.springbootrestapp.application.product.IProductRequest;


import java.math.BigDecimal;

// Requestler immutable (değiştirilemez) olmalıdır. Bu nedenle record kullanıyoruz.

public record CreateProductRequest(
        @JsonProperty("productName")
        String name,
        @JsonProperty("unitPrice")
        BigDecimal price,
        @JsonProperty("unitsInStock")
        int stock) implements IProductRequest {
}
