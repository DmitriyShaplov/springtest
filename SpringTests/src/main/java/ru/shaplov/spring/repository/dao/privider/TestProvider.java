package ru.shaplov.spring.repository.dao.privider;

import org.apache.ibatis.annotations.Param;
import ru.shaplov.spring.repository.entity.test.Test;
import ru.shaplov.spring.service.batch.impl.BatchServiceTestImpl;

import java.util.List;

public class TestProvider {

    public static String create(@Param("list") List<Test> list) {
        return BatchServiceTestImpl.SQL_MY_BATIS;
    }
}
