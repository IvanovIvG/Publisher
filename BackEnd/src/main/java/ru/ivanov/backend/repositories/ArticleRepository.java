package ru.ivanov.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.backend.models.Article;
import ru.ivanov.backend.models.Journal;

import java.util.List;
import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
    List<Article> findByJournal(Journal journal);
}
