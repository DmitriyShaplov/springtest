package ru.shaplov.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Dmitriy Shaplov
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public Object testAspectMethod(HttpSession session, @RequestParam(required = false) String username) {
        log.info("This is test");
        return "Test!";
    }
}