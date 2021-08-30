package ru.shaplov.spring.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shaplov.spring.repository.entity.ParentEntity;

public interface ParentRepository extends JpaRepository<ParentEntity, Long> {
}
