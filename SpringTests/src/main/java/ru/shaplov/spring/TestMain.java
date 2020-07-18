package ru.shaplov.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dmitriy Shaplov
 */
@SpringBootApplication
@MapperScan(basePackages = {"ru.shaplov.spring.repository.dao"})
public class TestMain {

    public static void main(String[] args) {
        SpringApplication.run(TestMain.class, args);
    }
}
