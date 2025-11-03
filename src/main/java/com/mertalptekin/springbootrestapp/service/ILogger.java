package com.mertalptekin.springbootrestapp.service;

// @Service, @Component gibi stereotype bean tanımları sadece class için kullanılır. İnterfacelerde kullanılmaz.
public interface ILogger {
    void log(String message);
}
