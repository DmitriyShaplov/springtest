package ru.shaplov.spring.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.util.FileCopyUtils;

/**
 * @author dshaplov
 */
public class TestTest {

    @Test
    public void test() {
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Test1 test1 = new Test1(1L, 1L, "1", "null", null);
        Test2 test2 = new Test2(null, 2L, null, "2", null);
        modelMapper.map(test1, test2);
    }

    @Test
    public void testModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(
                new PropertyMap<Test1, Test2>() {
                    @Override
                    protected void configure() {
                        skip(source.getTest(), destination.getTest());
                    }
                }
        );
//        modelMapper.typeMap(Test1.class, Test2.class)
//                .addMappings(mapper -> mapper.skip(Test2::setTest));
        Test1 test1 = new Test1(1L, 1L, "1", "null", new InnerTest1(1L, 2L, "1", "1"));
        Test2 test2 = modelMapper.map(test1, Test2.class);
    }

    @Test
    public void test2() throws JsonProcessingException {
        String json = "{\"test\":\"test\"}";
        Object o = new ObjectMapper().convertValue(json, Object.class);
    }

    @Test
    public void ttttttt() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        long startTime = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream("C:\\Users\\dshaplov\\Downloads\\Отчет. Подсистема Учет спецбланков.13.05.docx");
        byte[] res = FileCopyUtils.copyToByteArray(inputStream);
        System.out.println("length: " + res.length);
        long endTime = System.currentTimeMillis();
        System.out.println("seconds: " + (endTime - startTime) / 1000.0);
    }

    @Test
    public void tasdfa() {
        System.out.printf("%5d%n", 1);
    }

    @Test
    public void sadfa() {
        String x = null;
        switch (x) {
            case "null":
                break;
            default:
        }
    }

    public static void validatePersonStatus(Long personStatus, Long... validTypes) {
        for (Long validType : validTypes) {
            if (personStatus.equals(validType)) {
                return;
            }
        }
        throw new RuntimeException();
    }

    @Test
    public void test1441() {
        LocalDate asdf = LocalDate.parse("asdf");
    }

    @Test
    public void sssssssssss() {
        List<TTTTT> list = new ArrayList<>();
        list.add(new TTTTT());
        Map<String, String> collect = list.stream().collect(
                HashMap::new, (m, v) -> m.put(v.getA(), v.getB()), HashMap::putAll
        );

    }
}

@Data
class TTTTT {
    String a = "aaa";
    String b = null;
}

@Data
class Inputed {
    InputStream inputStream = new ByteArrayInputStream("asdf".getBytes());
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ListTest {
    List<Param> tests = Arrays.asList(new Param(), new Param());
}

@Data
class Param {
    String a = "a";
    String b = "b";
    Long c = 222L;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ListTest1 {
    String tests;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class TestDate {
    private OffsetDateTime offsetDateTime;
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private Instant instant;
    private ZonedDateTime zonedDateTime;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Test1 {
    @NotNull
    private Long id1;
    private Long id2;
    private String str1;
    private String str2;
    private InnerTest1 test;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class InnerTest1 {
    private Long id1;
    private Long id2;
    private String str1;
    private String str2;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Test2 {
    private Long id1;
    private Long id2;
    private String str1;
    private String str2;
    private InnerTest2 test;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class InnerTest2 {
    private Long id1;
    private Long id2;
    private String str1;
    private String str2;
}