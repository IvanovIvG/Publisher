package ru.ivanov.Publisher.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.Publisher.models.Article;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.repositories.ArticleRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Ivanov
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final JournalService journalService;

    @Transactional
    public Article create(Article article) {
        if(thereIsNoArticleWithSameId(article)){
            return articleRepository.save(article);
        }
        else{
            throw new IllegalArgumentException("There is already article with such id");
        }
    }

    public Article readById(int id) {
        Optional<Article> foundArticle = articleRepository.findById(id);
        return foundArticle.orElseThrow(() -> new IllegalArgumentException("There is no article with such id"));
    }

    public List<Article> readAll() {
        return articleRepository.findAll();
    }

    public List<Article> readAllByJournal(int journalId) {
        Journal journal = journalService.readById(journalId);
        return articleRepository.findByJournal(journal);
    }

    @Transactional
    public Article update(Article article) {
        if (thereIsArticleWithSameId(article)) {
            return articleRepository.save(article);
        }
        else{
            throw new IllegalArgumentException("There is no article with such id");
        }
    }

    @Transactional
    public void delete(int id){
        articleRepository.deleteById(id);
    }

    private boolean thereIsNoArticleWithSameId(Article article){
        return !thereIsArticleWithSameId(article);
    }

    private boolean thereIsArticleWithSameId(Article article){
        int articleId = article.getId();
        return articleRepository.findById(articleId).isPresent();
    }
}
