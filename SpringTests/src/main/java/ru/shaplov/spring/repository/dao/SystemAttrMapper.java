package ru.shaplov.spring.repository.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.shaplov.spring.repository.entity.SystemAttrEntity;
import ru.shaplov.spring.repository.entity.SystemAttrFilterInfo;

import java.util.List;

@Mapper
@Repository
public interface SystemAttrMapper {

    @Insert("INSERT INTO system_attr (name, actionInd) values (#{name}, #{actionInd})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(SystemAttrEntity entity);

    @Insert({"<script>",
            "INSERT INTO system_attr (name, actionInd) values",
            "<foreach collection='entities' item='entity' separator=','>",
            "(#{entity.name}, #{entity.actionInd})",
            "</foreach>",
            "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
//    @SelectKey(statement = "select nextval('system_attr_seq')", keyProperty = "id",
//            before = true, resultType = Long.class)
    void insertMultipleRows(@Param("entities") List<SystemAttrEntity> entities);

    @Results({
            @Result(column = "actionInd", property = "actionInd")
    })
    @Select("SELECT * FROM system_attr where id = #{id}")
    SystemAttrEntity read(@Param("id") long id);

    void update(SystemAttrEntity entity);

    void delete(long id);

    List<SystemAttrEntity> getAll(SystemAttrFilterInfo filterInfo);
}
