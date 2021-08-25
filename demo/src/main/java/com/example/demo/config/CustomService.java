package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CustomService {

    @Autowired
    public CustomBean<Integer> customBean;
    @Autowired
    public CustomBean<String> customBean2;

    @PostConstruct
    public void init() {
        System.out.println(customBean.getData());
    }
}
