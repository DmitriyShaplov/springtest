package ru.shaplov.spring.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.shaplov.spring.repository.entity.SystemAttrEntity;
import ru.shaplov.spring.repository.entity.test.Test;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {
    final DataSource dataSource;

    public MyBatisConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

//    @Bean("sqlSessionFactoryTest")
//    public SqlSessionFactory sqlSessionFactoryTest() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        SqlSessionFactory factory = factoryBean.getObject();
//        if (factory != null) {
//            org.apache.ibatis.session.Configuration configuration = factory.getConfiguration();
//            configuration.setMapUnderscoreToCamelCase(true);
//            configuration.setDefaultExecutorType(ExecutorType.BATCH);
//        }
//        return factoryBean.getObject();
//    }

    @Bean
    public MyBatisBatchItemWriter<Test> writer() throws Exception {
//        new SqlSessionTemplate();
        return new MyBatisBatchItemWriterBuilder<Test>()
                .sqlSessionFactory(sqlSessionFactory())
                .statementId("ru.shaplov.spring.repository.dao.TestMapper.create")
                .assertUpdates(false)
                .build();
    }
}
