package ru.shaplov.spring.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shaplov.spring.repository.entity.test.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
}
