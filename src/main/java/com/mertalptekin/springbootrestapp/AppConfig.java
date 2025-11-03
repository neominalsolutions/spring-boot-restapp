package com.mertalptekin.springbootrestapp;


import com.mertalptekin.springbootrestapp.service.TextLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean(name = "getAppName1")
    public String getAppName() {
        return "Spring Boot Rest App 01";
    }

    @Bean
    public TextLogger TextLogger() {
        return new TextLogger();
    }
}
