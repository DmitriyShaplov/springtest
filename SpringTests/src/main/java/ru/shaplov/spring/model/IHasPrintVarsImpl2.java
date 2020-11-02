package ru.shaplov.spring.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class IHasPrintVarsImpl2 implements IHasPrintVars {
    @Override
    public Map<String, String> getVars() {
        System.out.println(2);
        return new HashMap<>();
    }
}
