package ru.shaplov.spring.aspect;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;

import java.util.Collections;
import java.util.List;

public class TestedAspectTest {

    private final SimpleAttributes2GrantedAuthoritiesMapper authoritiesMapper = new SimpleAttributes2GrantedAuthoritiesMapper();

    @Test
    public void test() {
        List<GrantedAuthority> roleAdmin = authoritiesMapper.getGrantedAuthorities(Collections.singletonList("ROLE_ADMIN"));
        System.out.println(roleAdmin.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}