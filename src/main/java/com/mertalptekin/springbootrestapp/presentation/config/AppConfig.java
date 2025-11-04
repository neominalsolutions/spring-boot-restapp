package com.mertalptekin.springbootrestapp.presentation.config;


import com.mertalptekin.springbootrestapp._demo.springContext.logger.TextLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration sınıfında manuel bean tanımlamaları yapılabilir.
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
