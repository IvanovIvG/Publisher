package ru.ivanov.Publisher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.Publisher.models.Article;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.repositories.ArticleRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Ivan Ivanov
 **/
@Service
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final JournalService journalService;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, JournalService journalService) {
        this.articleRepository = articleRepository;
        this.journalService = journalService;
    }

    @Transactional
    public void create(Article article) {
        articleRepository.save(article);
    }

    public Article readById(int id) {
        Optional<Article> foundArticle = articleRepository.findById(id);
        return foundArticle.orElseThrow(() -> new NoSuchElementException("There is no article with such id"));
    }

    public List<Article> readAll() {
        return articleRepository.findAll();
    }

    public List<Article> readAllByJournal(int journalId) {
        Journal journal = journalService.readById(journalId);
        return articleRepository.findByJournal(journal);
    }

    @Transactional
    public void update(Article article) {
        Optional<Article> articleWithSameId = articleRepository.findById(article.getId());
        if (articleWithSameId.isPresent()) {
            articleRepository.save(article);
        }
    }

    @Transactional
    public void delete(int id){
        articleRepository.deleteById(id);
    }
}
