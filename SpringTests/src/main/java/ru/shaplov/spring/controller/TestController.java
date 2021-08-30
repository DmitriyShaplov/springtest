package ru.shaplov.spring.controller;

import example.model.TestDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shaplov.spring.aspect.ExecuteAspect;
import ru.shaplov.spring.model.IHasPrintVars;
import ru.shaplov.spring.model.IHasPrintVarsImpl;
import ru.shaplov.spring.repository.dao.SpringMapper;
import ru.shaplov.spring.repository.dao.SystemAttrMapper;
import ru.shaplov.spring.repository.dao.TestMapper;
import ru.shaplov.spring.repository.dao.TestXMLMapper;
import ru.shaplov.spring.repository.entity.ActionIndicatorEnum;
import ru.shaplov.spring.repository.entity.SystemAttrEntity;
import ru.shaplov.spring.repository.entity.test.Test;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;

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
    @Autowired
    private IHasPrintVars iHasPrintVars;

    @Value("#{servletContext.contextPath + '${mybatis.type-handlers-package}'}")
    private String servletContextPath;

    @GetMapping("/aspect")
    public void test() {
        new IHasPrintVarsImpl().getVars();
        IHasPrintVars i = HashMap::new;
        i.getVars();
        iHasPrintVars.getVars();
    }

    @GetMapping("/test")
    @ExecuteAspect
    @Secured("ROLE_admin.eeE")
    public UnitUser testAspectMethod(HttpSession session, @RequestParam(required = false) String username) {
        log.info("This is test");
        log.debug("{}", session.getAttribute("aspect"));
        UnitUser unitUser = null;
        return unitUser;
    }

    @PostMapping("/post")
    public void test(@Validated @RequestBody DLObject object) {
        System.out.println(object.getInner().getData());
    }

    @PostMapping(value = "/compress", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> compress(@RequestPart MultipartFile file) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (DeflaterOutputStream dout = new DeflaterOutputStream(bout)) {
            dout.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", String.format("attachment; filename=\"%s.%s\"", "123", "png"))
                .header("Content-Description", "File Transfer")
                .body(bout.toByteArray());
    }

    @Data
    private static class DLObject {
        @Valid
        private InnerObject inner;
    }

    @Data
    private static class InnerObject {
        @NotNull
        private String data;
    }

    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        Test test = new Test(2L, null, "teee", "teee", null);
        testMapper.update(test);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/date/now")
    public TestDate date() {
        return new TestDate();
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