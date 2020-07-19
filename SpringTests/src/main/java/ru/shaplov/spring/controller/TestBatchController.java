package ru.shaplov.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shaplov.spring.repository.entity.test.Test;
import ru.shaplov.spring.service.batch.BatchService;

import java.util.ArrayList;
import java.util.List;

import static ru.shaplov.spring.service.batch.impl.BatchServiceTestImpl.testList;

@RestController
@RequestMapping("/batch")
public class TestBatchController {

    private final BatchService batchService;

    public TestBatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/execute")
    public ResponseEntity<String> executeBatch() {

        batchService.importJdbcBatch(testList);
        batchService.importBatchJdbcTemplate(testList);
        batchService.importBatchNamedParameterJdbcTemplate(testList);
        batchService.importMyBatisBatchType(testList);
        batchService.importMyBatisValuesChunks(testList);
//        batchService.importMyBatisCopyImports(testList);
//        batchService.importPreparedString(testList);
        batchService.importMyBatisBatchWriter(testList);

        batchService.importJdbcBatch(testList);
        batchService.importBatchJdbcTemplate(testList);
        batchService.importBatchNamedParameterJdbcTemplate(testList);
        batchService.importMyBatisBatchType(testList);
        batchService.importMyBatisValuesChunks(testList);
//        batchService.importMyBatisCopyImports(testList);
//        batchService.importPreparedString(testList);
        batchService.importMyBatisBatchWriter(testList);

        return ResponseEntity.ok("ok");
    }
}
