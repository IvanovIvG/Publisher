package ru.ivanov.Publisher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.Publisher.models.Test;

/**
 * @author Ivan Ivanov
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
}
