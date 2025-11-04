package com.mertalptekin.springbootrestapp.application.product.discountPrice;

import com.mertalptekin.springbootrestapp.application.product.IProductRequestHandler;
import com.mertalptekin.springbootrestapp.domain.entity.Product;
import com.mertalptekin.springbootrestapp.domain.service.product.IProductService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DiscountPriceHandler implements IProductRequestHandler<DiscountPriceRequest,DiscountPriceResponse> {

    private final IProductService productService;
    private ApplicationEventPublisher eventPublisher; // Monolith uygulamalarda event driven yapıyı destekler.

    public DiscountPriceHandler(IProductService productService, ApplicationEventPublisher eventPublisher) {
        this.productService = productService;
        this.eventPublisher = eventPublisher;
    }

    // UseCase  -> Bütün Fiyat değişimlerini, fiyat güncellmeleri yapldığında ProductPrices tablosunda History olarak kaydetmek istiyoruz. UseCase

    @Override
    public DiscountPriceResponse handle(DiscountPriceRequest request) {

        Product entity =  this.productService.getProductById(request.id()); // Check if product exists
        BigDecimal oldPrice = entity.getPrice();
        entity.setPrice(request.newPrice());
        this.productService.updateProduct(entity);
        // Event fırlat ProductPrices tablosuna insert için.

        // Buradaki eventi dinliyip işlem yapacak olan bir listener ihtiyacımız var.
        // event fırlatma
        this.eventPublisher.publishEvent(new DiscountPriceEvent(this, oldPrice, request.newPrice()));

        return new DiscountPriceResponse(entity.getId(), oldPrice, request.newPrice());
    }
}
