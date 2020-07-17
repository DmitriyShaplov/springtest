package ru.shaplov.spring.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import ru.shaplov.spring.repository.entity.test.Test;
import ru.shaplov.spring.repository.entity.test.TestAttr;

import java.util.List;

@Mapper
@Repository
public interface TestXMLMapper {
    Test getTestById(Long id);
    List<TestAttr> getAttrs(Long id);
}
