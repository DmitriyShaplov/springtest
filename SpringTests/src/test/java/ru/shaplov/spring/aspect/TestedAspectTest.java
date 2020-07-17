package ru.shaplov.spring.aspect;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        for (int i = 0; i < 990; i++) {
            listInt.add(i);
        }
        List<List<Integer>> partition = Lists.partition(listInt, 100);
    }
}