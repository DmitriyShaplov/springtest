package ru.shaplov.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shaplov.spring.repository.dao.ParentRepository;
import ru.shaplov.spring.repository.entity.ChildEntity;
import ru.shaplov.spring.repository.entity.ParentEntity;

import java.util.List;

@Service
public class ParentServiceImpl {

    @Autowired
    private ParentRepository parentRepository;

    @Transactional
    public ParentEntity create(ParentEntity entity) {
        ParentEntity save = parentRepository.save(entity);
        return save;
    }

    @Transactional
    public ParentEntity update(ParentEntity entity) {
        ParentEntity parentEntity = parentRepository.findById(entity.getId()).orElseThrow();
        List<ChildEntity> children = parentEntity.getChildren();
        children.clear();
        children.addAll(entity.getChildren());
        return parentEntity;
    }

    @Transactional
    public ParentEntity update2(ParentEntity entity) {
        ParentEntity parentEntity = parentRepository.findById(entity.getId()).orElseThrow();
        List<ChildEntity> children = parentEntity.getChildren();
        List<ChildEntity> newChildrens = entity.getChildren();
        children.retainAll(newChildrens);
        newChildrens.removeAll(children);
        children.addAll(newChildrens);
        return parentEntity;
    }
}
