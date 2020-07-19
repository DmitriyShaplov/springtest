package ru.shaplov.spring.repository.entity.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    public Test(String name, String description, String info) {
        this.name = name;
        this.description = description;
        this.info = info;
    }

    private Long id;
    private String name;
    private String description;
    private String info;
    private List<TestAttr> attrs;
}
