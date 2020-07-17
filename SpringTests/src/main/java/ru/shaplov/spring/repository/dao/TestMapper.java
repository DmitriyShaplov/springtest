package ru.shaplov.spring.repository.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.shaplov.spring.repository.entity.test.Test;
import ru.shaplov.spring.repository.entity.test.TestAttr;

import java.util.ArrayList;

@Mapper
@Repository
public interface TestMapper {

    @Results(id = "TestMap", value = {
            @Result(property = "id", column = "idTest"),
            @Result(property = "name", column = "nameTest"),
            @Result(property = "attrs", javaType = ArrayList.class,
            column = "id",
            many = @Many(resultMap = "AttrMap"))
    })
    @Select({"select t.id as idTest, t.name as nameTest,",
            "a.id as idAttr, a.name as nameAttr",
            "FROM test as t inner join test_attr a on t.id = a.test_id where t.id = #{id}"})
    Test getTestById(Long id);

    @Results(id = "AttrMap", value = {
            @Result(property = "id", column = "idAttr"),
            @Result(property = "name", column = "nameAttr")
    })
    @Select({"select 1"})
    TestAttr getAttr();
}
