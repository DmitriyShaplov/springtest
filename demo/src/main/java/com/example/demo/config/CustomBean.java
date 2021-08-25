package com.example.demo.config;

import lombok.Data;

@Data
public class CustomBean<T> {
    private T data;
}
