package ru.shaplov.spring.service.batch;

import ru.shaplov.spring.repository.entity.test.Test;

import java.util.List;

public interface BatchService {

    void importJdbcBatch(List<Test> list);
    void importBatchJdbcTemplate(List<Test> list);
    void importBatchNamedParameterJdbcTemplate(List<Test> list);
    void importMyBatisBatchType(List<Test> list);
    void importMyBatisBatchWriter(List<Test> list);
    void importMyBatisValuesChunks(List<Test> list);
    void importMyBatisCopyImports(List<Test> list);
    void importPreparedString(List<Test> list);
}
