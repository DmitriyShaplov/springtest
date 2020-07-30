package ru.shaplov.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Тут поднимается весь необходимый веб контекст для
 * этого контроллера, вместе со всеми request mapping, message converters, validators etc....
 */
@WebMvcTest(TestController.class)
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = TestController.class) //Нужна если нет мейн класса SpringBoot
public class TestControllerTestSpringContext {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; // - тут подхватится ObjectMapper который реально в контроллере объекты десериализует.

//    @MockBean мокаем сервисы
}