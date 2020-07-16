package ru.shaplov.spring.repository.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaplov.spring.repository.entity.ActionIndicatorEnum;
import ru.shaplov.spring.repository.entity.SystemAttrEntity;

import java.util.List;

@Service
public class SpringMapper {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private MyBatisBatchItemWriter<SystemAttrEntity> myBatisBatchItemWriter;

    /**
     * Батч через MyBatisBatchItemWriter (нужно подключать Spring Batch dependencies)
     */
    public void batchWriter(List<SystemAttrEntity> entities) {
        myBatisBatchItemWriter.write(entities);
    }

    /**
     * JDBC батч без ограничения количества вставок.
     */
    public void batchInsertNew(List<SystemAttrEntity> entities) {
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            SystemAttrMapper mapper = session.getMapper(SystemAttrMapper.class);
            entities.forEach(mapper::create);
            session.commit();
        }
    }

    /**
     * JDBC батч с ограничением количества вставок.
     */
    public void batchInsert(List<SystemAttrEntity> entities) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        SystemAttrMapper mapper = sqlSession.getMapper(SystemAttrMapper.class);
        try {
            for (int i = 0; i < entities.size(); i++) {
                SystemAttrEntity entity = new SystemAttrEntity("Test", ActionIndicatorEnum.INSERT);
                mapper.create(entity);
                if ((i + 1) % 5 == 0 || i == entities.size() - 1) {
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
