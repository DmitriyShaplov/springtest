package ru.shaplov.spring.service.batch.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;
import ru.shaplov.spring.repository.dao.TestMapper;
import ru.shaplov.spring.repository.entity.test.Test;
import ru.shaplov.spring.service.batch.BatchService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BatchServiceTestImpl implements BatchService {

    private static final String SQL = "INSERT INTO test (name, description, info) VALUES (?, ?, ?)";
    private static final int BATCH_SIZE = 1000;
    private static final int TEST_SIZE = 10;
    public static final String SQL_MY_BATIS;
    public static final List<Test> testList = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final DataSource dataSource;
    private final SqlSessionFactory sqlSessionFactory;
    private final MyBatisBatchItemWriter<Test> myBatisBatchItemWriter;
    private final TestMapper testMapper;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < TEST_SIZE; i++) {
            stringBuilder.append("INSERT INTO test (name, description, info) " + "VALUES (#{list[")
                    .append(i).append("].name}, #{list[").append(i)
                    .append("].description}, #{list[")
                    .append(i).append("].info});");
        }
        SQL_MY_BATIS = stringBuilder.toString();
    }

    static {
        Test test = new Test();
        test.setName("test name");
        test.setDescription("test description");
        test.setInfo("test info");
        for (int i = 0; i < TEST_SIZE; i++) {
            testList.add(test);
        }
    }

    public BatchServiceTestImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource, SqlSessionFactory sqlSessionFactory, MyBatisBatchItemWriter<Test> myBatisBatchItemWriter, TestMapper testMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.dataSource = dataSource;
        this.sqlSessionFactory = sqlSessionFactory;
        this.myBatisBatchItemWriter = myBatisBatchItemWriter;
        this.testMapper = testMapper;
    }

    @Override
    public void importJdbcBatch(List<Test> list) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                for (int count = 0; count < list.size(); count++) {
                    Test v = list.get(count);
                    preparedStatement.setString(1, v.getName());
                    preparedStatement.setString(2, v.getDescription());
                    preparedStatement.setString(3, v.getInfo());
                    if (count % BATCH_SIZE == 0) {
                        preparedStatement.executeBatch();
                    }
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                log.error("jdbc fails. {}", e.getMessage(), e);
            }
        } catch (SQLException e) {
            log.error("jdbc fails. {}", e.getMessage(), e);
        }
    }

    @Override
    public void importBatchJdbcTemplate(List<Test> list) {
        jdbcTemplate.batchUpdate(SQL,
                list,
                BATCH_SIZE,
                (ps, i) -> {
                    ps.setString(1, i.getName());
                    ps.setString(2, i.getDescription());
                    ps.setString(3, i.getInfo());
                });
    }

    @Override
    public void importBatchNamedParameterJdbcTemplate(List<Test> list) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list);
        namedParameterJdbcTemplate.batchUpdate(
                "insert into test (name, description, info) values (:name, :description, :info)",
                batch
        );
    }

    @Override
    public void importMyBatisBatchType(List<Test> list) {
        final SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            final TestMapper mapper = sqlSession.getMapper(TestMapper.class);
            for (int i = 0; i < list.size(); i++) {
                Test test = list.get(i);
                mapper.create(test);
                if (i % BATCH_SIZE == 0) {
                    sqlSession.flushStatements();
                }
            }
            sqlSession.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void importMyBatisBatchWriter(List<Test> list) {
        myBatisBatchItemWriter.write(list);
    }

    @Override
    public void importMyBatisValuesChunks(List<Test> list) {
        List<List<Test>> partition = Lists.partition(list, 100);
        partition.forEach(testMapper::insertMultipleValues);
    }

    @Override
    public void importMyBatisCopyImports(List<Test> list) {
        testMapper.insertMultipleRows(list);
    }

    @Override
    public void importPreparedString(List<Test> list) {
        testMapper.insertSQLString(list);
    }
}
