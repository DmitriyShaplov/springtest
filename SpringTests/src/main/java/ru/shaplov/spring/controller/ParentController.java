package ru.shaplov.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shaplov.spring.repository.entity.ParentEntity;
import ru.shaplov.spring.service.ParentServiceImpl;

@RestController
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    private ParentServiceImpl parentService;

    @PostMapping
    public ParentEntity create(@RequestBody ParentEntity entity) {
        return parentService.create(entity);
    }

    @PutMapping
    public ParentEntity update(@RequestBody ParentEntity entity) {
        return parentService.update(entity);
    }

    @PutMapping("/v2")
    public ParentEntity update2(@RequestBody ParentEntity entity) {
        return parentService.update2(entity);
    }
}
