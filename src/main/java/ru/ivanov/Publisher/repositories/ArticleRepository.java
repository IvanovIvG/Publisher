package ru.ivanov.Publisher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.Publisher.models.Article;
import ru.ivanov.Publisher.models.Journal;

import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByJournal(Journal journal);
}
