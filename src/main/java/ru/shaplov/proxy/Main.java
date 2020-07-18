package ru.shaplov.proxy;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        System.out.println(String.class.getDeclaredConstructor(String.class).newInstance("asdfas"));
        Method method;
    }
}
