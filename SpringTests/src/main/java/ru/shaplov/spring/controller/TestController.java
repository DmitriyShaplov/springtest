package ru.shaplov.spring.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Value("#{servletContext.contextPath + '${mybatis.type-handlers-package}'}")
    private String servletContextPath;

    @GetMapping("/test")
    @ExecuteAspect
    @Secured("ROLE_admin.eeE")
    public UnitUser testAspectMethod(HttpSession session, @RequestParam(required = false) String username) {
        log.info("This is test");
        log.debug("{}", session.getAttribute("aspect"));
        UnitUser unitUser = null;
        return unitUser;
    }

    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/redirect1")
    public void redirect1(HttpServletResponse response) throws IOException {
        response.sendRedirect("/spring/ok");
    }

    @GetMapping("/redirect2")
    public void redirect2(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8881/spring/ok");
    }

    @GetMapping("/redirect3")
    public ResponseEntity<String> redirect3() throws IOException {
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/spring/ok").build();
    }

    @GetMapping("/redirect4")
    public ResponseEntity<String> redirect4() throws IOException {
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "http://localhost:8881/spring/ok").build();
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
//        Test test1 = testXMLMapper.getTestById(1L);
        testMapper.getUUID(UUID.fromString("8510cba8-d54a-40b0-a6fc-58937745996c"));
        System.out.println("test");
        return "ok ";
    }

    @GetMapping("/date")
    public LocalDate test3() {
        return null;
    }

    public static class UUIDtest {
        private long id;

        private UUID uuid;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }
    }
}

@Getter
@Setter
class UnitUser {
    private String username;
    private String description;
}