package ru.shaplov.spring.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestedAspectTest {

    private final SimpleAttributes2GrantedAuthoritiesMapper authoritiesMapper = new SimpleAttributes2GrantedAuthoritiesMapper();

    @Test
    public void test() {
        List<GrantedAuthority> roleAdmin = authoritiesMapper.getGrantedAuthorities(Collections.singletonList("ROLE_ADMIN"));
        System.out.println(roleAdmin.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    public void sssss() {
        List<Integer> listInt = new ArrayList<>();
        List<Integer> collect = Stream.iterate(0, (v) -> v + 1).limit(1000).collect(Collectors.toList());
        List<List<Integer>> partition = Lists.partition(listInt, 100);
    }

    @Test
    @SneakyThrows
    public void ss() {
        ru.shaplov.spring.repository.entity.test.Test test = new ru.shaplov.spring.repository.entity.test.Test();
        test.setName("name test!");
        Field name = ru.shaplov.spring.repository.entity.test.Test.class.getDeclaredField("name");
        name.setAccessible(true);
        System.out.println(name.get(test));
    }

    public static class Test1 {
        public Integer num;
    }
}