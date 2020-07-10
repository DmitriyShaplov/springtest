package ru.shaplov.spring.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shaplov.spring.aspect.ExecuteAspect;

import javax.servlet.http.HttpSession;

/**
 * @author Dmitriy Shaplov
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    @ExecuteAspect
    @Secured("ROLE_admin.eeE")
    public UnitUser testAspectMethod(HttpSession session, @RequestParam(required = false) String username) {
        log.info("This is test");
        log.debug("{}", session.getAttribute("aspect"));
        UnitUser unitUser = null;
        return unitUser;
    }

    @GetMapping("/test1")
    public Object testWOAspect(HttpSession session) {
        return "Test wo!";
    }
}

@Getter
@Setter
class UnitUser {
    private String username;
    private String description;
}