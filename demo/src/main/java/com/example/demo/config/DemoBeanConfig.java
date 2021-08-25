package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoBeanConfig {

    @Bean
    public CustomBean<Object> customBeanObject() {
        return new CustomBean<>().setData("object");
    }
    @Bean
    public CustomBean<?> customBeanT() {
        return new CustomBean<>();
    }
}
