package ru.shaplov.spring.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
}