package ru.shaplov.spring.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shaplov.spring.repository.entity.SystemAttrEntity;

@Configuration
public class MyBatisConfig {
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    SqlSessionTemplate template;

    @Bean
    public MyBatisBatchItemWriter<SystemAttrEntity> writer() {
        return new MyBatisBatchItemWriterBuilder<SystemAttrEntity>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("ru.shaplov.spring.repository.dao.SystemAttrMapper.create")
                .assertUpdates(false)
                .build();
    }
}
