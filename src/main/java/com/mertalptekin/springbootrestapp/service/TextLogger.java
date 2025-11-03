package com.mertalptekin.springbootrestapp.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Not: @Service,@Controller,@Repository dışında bir hizmet ise @Component kullanabiliriz. @Component genel bir stereotype bean tanımıdır.
// Qualifier ile hangi bean ın kullanılacağını belirleyebiliriz.

@Component("textLogger") // Bean ismi ILogger olarak tanımlandı
public class TextLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.println("TextLogger : " + message);
    }
}
