package com.mertalptekin.springbootrestapp.application.product.getProductById;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mertalptekin.springbootrestapp.application.product.IProductResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class GetProductByIdResponse implements IProductResponse {
    @JsonProperty("productId")
    private  Integer id;
    @JsonProperty("productName")
    private  String name;
    @JsonProperty("unitPrice")
    private  double price;
    @JsonProperty("unitsInStock")
    private  int stock;
}