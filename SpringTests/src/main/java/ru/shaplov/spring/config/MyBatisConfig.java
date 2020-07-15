package ru.shaplov.spring.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBatisConfig {
    @Autowired
    SqlSessionFactory sqlSessionFactory;
}
