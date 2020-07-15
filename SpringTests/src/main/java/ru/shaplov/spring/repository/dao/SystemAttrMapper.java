package ru.shaplov.spring.repository.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.shaplov.spring.repository.entity.SystemAttrEntity;
import ru.shaplov.spring.repository.entity.SystemAttrFilterInfo;
import ru.shaplov.spring.repository.typehandler.ActionIndTypeHandler;

import java.util.List;

@Mapper
@Repository
public interface SystemAttrMapper {

    @Insert("INSERT INTO system_attr (name, actionInd) values (#{name}, #{actionInd, " +
            "typeHandler=ru.shaplov.spring.repository.typehandler.ActionIndTypeHandler})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(SystemAttrEntity entity);

    @Results({
            @Result(column = "actionInd", property = "actionInd", typeHandler = ActionIndTypeHandler.class)
    })
    @Select("SELECT * FROM system_attr where id = #{id}")
    SystemAttrEntity read(@Param("id") long id);

    void update(SystemAttrEntity entity);

    void delete(long id);

    List<SystemAttrEntity> getAll(SystemAttrFilterInfo filterInfo);
}
