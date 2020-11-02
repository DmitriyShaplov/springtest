package ru.shaplov.spring.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

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
        String json = "2020-06-29 17:06:16.080+0300";
        System.out.println(OffsetDateTime.now().toString());
        ObjectMapper objectMapper = new ObjectMapper();

    }

    public static void validatePersonStatus(Long personStatus, Long... validTypes) {
        for (Long validType : validTypes) {
            if (personStatus.equals(validType)) {
                return;
            }
        }
        throw new RuntimeException();
    }
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