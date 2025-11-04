package com.mertalptekin.springbootrestapp.application.product.discountPrice;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DiscountPriceListener {

    // Method eventin fırlatıldığı anı dinler.

    @EventListener
    public void discountPrice(DiscountPriceEvent event) {
        // Burada DiscountPriceEvent tetiklendiğinde yapılacak işlemler yer alır.
        // ProductPrices tablosuna insert işlemi yapılabilir.
        System.out.println("Old Price: " + event.oldPrice + ", New Price: " + event.newPrice);
    }

    @EventListener
    public void SendEmail(DiscountPriceEvent event) {
        // Fiyat değiştiğinde email gönderme işlemi yapılabilir.
        System.out.println("Sending email about price change from " + event.oldPrice + " to " + event.newPrice);
    }


}
