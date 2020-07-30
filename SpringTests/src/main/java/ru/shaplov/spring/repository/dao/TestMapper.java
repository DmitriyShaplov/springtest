package ru.shaplov.spring.repository.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import ru.shaplov.spring.controller.TestController;
import ru.shaplov.spring.repository.dao.provider.TestProvider;
import ru.shaplov.spring.repository.entity.test.Test;
import ru.shaplov.spring.repository.entity.test.TestAttr;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface TestMapper {

    @Insert("insert into test (name, description, info) values (#{name}, #{description}, #{info})")
    void create(Test test);

    @Insert({"<script>",
            "<foreach collection='entities' item='entity' separator=';'>",
            "INSERT INTO test (name, description, info) values",
            "(#{entity.name}, #{entity.description}, #{entity.info})",
            "</foreach>",
            "</script>"
    })
    void insertMultipleRows(@Param("entities") List<Test> entities);

    @Insert({"<script>",
            "INSERT INTO test (name, description, info) values",
            "<foreach collection='entities' item='entity' separator=','>",
            "(#{entity.name}, #{entity.description}, #{entity.info})",
            "</foreach>",
            "</script>"
    })
    void insertMultipleValues(@Param("entities") List<Test> entities);

    @Insert({"<script>",
            "INSERT INTO test (name, description, info) values",
            "<foreach collection='entities' item='entity' separator=','>",
            "(#{entity.name}, #{entity.description}, #{entity.info})",
            "</foreach>",
            "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertMultipleValuesIdGenerated(@Param("entities") List<Test> entities);

    @InsertProvider(value = TestProvider.class, method = "create")
    void insertSQLString(@Param("list") List<Test> list);

    @Results(id = "TestMap", value = {
            @Result(property = "id", column = "idTest"),
            @Result(property = "name", column = "nameTest"),
            @Result(property = "attrs",
            column = "id",
            one = @One(resultMap = "AttrMap"))
    })
    @Select({"select t.id as idTest, t.name as nameTest,",
            "a.id as idAttr, a.name as nameAttr",
            "FROM test as t inner join test_attr a on t.id = a.test_id where t.id = #{id}"})
    Test getTestById(Long id);

    @Results(id = "AttrMap", value = {
            @Result(property = "id", column = "idAttr"),
            @Result(property = "name", column = "nameAttr")
    })
    @Select("select 1")
    TestAttr getAttr();

    /**
********************************************
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "attrs", javaType = List.class,
                    column = "id",
                    many = @Many(select = "getAttrs", fetchType = FetchType.LAZY))
    })
    @Select("select * from test where id = #{id}")
    Test getTest(Long id);

    @Select("select * from test_attr where test_id = #{id}")
    List<TestAttr> getAttrs(Long id);

    @Insert("INSERT INTO uuid_test (task_id) values (#{uuid})")
    void insertUuid(@Param("uuid") UUID uuid);

    @Select("SELECT * FROM uuid_test where task_id = #{uuid}")
    TestController.UUIDtest getUUID(@Param("uuid") UUID uuid);
}
