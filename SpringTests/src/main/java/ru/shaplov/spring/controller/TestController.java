package ru.shaplov.spring.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shaplov.spring.aspect.ExecuteAspect;
import ru.shaplov.spring.repository.dao.SpringMapper;
import ru.shaplov.spring.repository.dao.SystemAttrMapper;
import ru.shaplov.spring.repository.dao.TestMapper;
import ru.shaplov.spring.repository.dao.TestXMLMapper;
import ru.shaplov.spring.repository.entity.ActionIndicatorEnum;
import ru.shaplov.spring.repository.entity.SystemAttrEntity;
import ru.shaplov.spring.repository.entity.test.Test;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Shaplov
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private SystemAttrMapper systemAttrMapper;
    @Autowired
    private SpringMapper springMapper;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TestXMLMapper testXMLMapper;

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
        List<SystemAttrEntity> entities = new ArrayList<>();
        entities.add(new SystemAttrEntity("test1", ActionIndicatorEnum.UPDATE));
        entities.add(new SystemAttrEntity("test2", ActionIndicatorEnum.INSERT));
        entities.add(new SystemAttrEntity("test3", ActionIndicatorEnum.DELETE));
        springMapper.batchInsert(entities);
        return "Test wo!";
    }

    @GetMapping("test2")
    public Object test2() {
        Test test = testMapper.getTestById(1L);
        Test test1 = testXMLMapper.getTestById(1L);
        Test test3 = testMapper.getTest(1L);
        return "ok";
    }
}

@Getter
@Setter
class UnitUser {
    private String username;
    private String description;
}