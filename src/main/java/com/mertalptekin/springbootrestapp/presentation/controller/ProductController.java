package com.mertalptekin.springbootrestapp.presentation.controller;


import com.mertalptekin.springbootrestapp.application.product.IProductRequestHandler;
import com.mertalptekin.springbootrestapp.application.product.createProduct.CreateProductRequest;
import com.mertalptekin.springbootrestapp.application.product.createProduct.CreateProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/products") // Genel product endpoint'i
public class ProductController {

    // DIP'e uygun olarak handler interface'i üzerinden bağımlılık enjekte ediliyor.
    // Presentation katmanı, Application katmanına bağımlıdır. Application katmanındaki handler interface'ini kullanılara Application katmanındanı çağrı zayıf bağımlılık oluşturulmuş olur.
    private final IProductRequestHandler<CreateProductRequest, CreateProductResponse> productCreateRequestHandler;

    public ProductController(IProductRequestHandler<CreateProductRequest,CreateProductResponse> productCreateRequestHandler) {
        this.productCreateRequestHandler = productCreateRequestHandler;
    }

    // Controller katmanında sadece istekleri karşılayıp ilgili handler'a yönlendirme yapılır.
    // POST /api/products
    // Görevi ProductEntity ile ilgili useCaselere isteklerin yönlendirilmesidir.
    // Post,PUT işlemlerde RequestBody'nin validayonunu otomatik yönetmektir.
    // Event ve Request işlemleri için genel olarak modern yaklaşımlarda record yapısı tercih edilmektedir.
    // POST işlemlerinde genel yapı 201 Created dönmektir.
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        // Ürün oluşturma işlemleri burada yapılacak

        var response = this.productCreateRequestHandler.handle(request);

        var uri = URI.create("/api/products/" + response.getId()); // Oluşturulan kaynağın URI'si

        return ResponseEntity.created(uri).body(response);
    }


}
