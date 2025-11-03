package com.mertalptekin.springbootrestapp.service;

import org.springframework.stereotype.Component;

@Component("dbLogger") // Bean ismi dbLogger olarak tanımlandı
public class DbLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.println("DbLogger -> :" + message);
    }
}
