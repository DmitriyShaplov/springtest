package ru.shaplov.spring.repository.entity.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    private Long id;
    private String name;
    private String description;
    private String info;
    private List<TestAttr> attrs;
}
