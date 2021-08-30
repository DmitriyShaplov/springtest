package ru.shaplov.spring.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Здесь мы просто мокаем контроллер, он будет настроен standalone setup и будет отвечать на HTTP запросы.
 * Но при этом здесь не поднимаются никакие конвертеры и тд.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestControllerTestMock {

    private MockMvc mockMvc;

    @InjectMocks
    private TestController testController;

//    @Mock мокаем бины таким образом

    @Before
    public void init() {
//        MockitoAnnotations.initMocks(this); Аннотацию юзаем или этот метод.
        mockMvc = MockMvcBuilders.standaloneSetup(testController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    public static final int SEQUENCE_ORDER = 100000;
    private static final String DELIMITER = ",";
    private static final int SYMBOLS_IN_PETITION_NUMBER = 5;

    @Test
    public void test() {
        Pattern compile = Pattern.compile("s:(.+?):");
        Matcher matcher = compile.matcher("s:gismu_user_01:a27fede0:172.18.4.69:1596536855711:gismu-visa-dev");
        boolean b = matcher.find();
        if (b) {
            System.out.println(matcher.group(1));
            System.out.println(String.format("pattrn: %s", compile.pattern()));
        }
    }

    @Test
    public void testPetitionNo() {
        System.out.println(generatePetitionNumber("G52", 101));
    }

    private String generatePetitionNumber(String invitationCode, int sequenceNextValue) {
        int prefixIndex = sequenceNextValue / SEQUENCE_ORDER;
        int petitionNumber = sequenceNextValue % SEQUENCE_ORDER;
        String[] invitationCodes = invitationCode.split(DELIMITER);
        if (prefixIndex < invitationCodes.length) {
            return invitationCodes[prefixIndex] +
                    StringUtils.leftPad(String.valueOf(petitionNumber), SYMBOLS_IN_PETITION_NUMBER, '0');
        }
        return null;
    }

    @Test
    public void test111() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Integer integer = list.get(0);
        integer = 5;

    }

    @Test
    public void testMap() {
        List<Pair> pairs = Collections.singletonList(new Pair(1L, null));
        Map<Long, Long> collect = pairs.stream().collect(Collectors.toMap(Pair::getId, (pair) -> null, (o1, o2) -> o2));
    }

    @Test
    public void test13() {
        String s = null;
        ModelMapper map = new ModelMapper() {
            @Override
            public <D> D map(Object source, Class<D> destinationType) {
                return super.map(source, destinationType);
            }
        };
        String map1 = map.map(s, String.class);
        System.out.println(map1);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testList() {
        Random random1 = new Random();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
            System.out.println(random1.nextInt());
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Pair {
        Long id;
        Long id2;
    }

    static class Test1 {
        LocalDate startDate;
        LocalDate endDate;

        public Test1() {
            startDate = LocalDate.now();
            endDate = LocalDate.now();
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }
    }

    static class Test2 {
        LocalDate dateStart;
        LocalDate dateEnd;

        public LocalDate getDateStart() {
            return dateStart;
        }

        public LocalDate getDateEnd() {
            return dateEnd;
        }

        public void setDateStart(LocalDate dateStart) {
            this.dateStart = dateStart;
        }

        public void setDateEnd(LocalDate dateEnd) {
            this.dateEnd = dateEnd;
        }
    }
}