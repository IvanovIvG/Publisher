package ru.ivanov.Publisher.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.Publisher.models.Article;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ivan Ivanov
 **/
@Component
class ArticleRepositoryTest {
    private final ArticleRepository articleRepository;

    @Autowired
    ArticleRepositoryTest(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Test
    public void getByIdReturnCorrectEntity(){
        int id = 1;

        Optional<Article> article = articleRepository.findById(id);

        Assertions.assertTrue(article.isPresent());
    }
}