package ru.shaplov.spring.model;

import java.util.HashMap;
import java.util.Map;

public class IHasPrintVarsImpl implements IHasPrintVars {
    @Override
    public Map<String, String> getVars() {
        System.out.println(1);
        return new HashMap<>();
    }
}
