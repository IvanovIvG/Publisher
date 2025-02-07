package ru.ivanov.Publisher.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.Publisher.dto.ArticleDTO;
import ru.ivanov.Publisher.dto.JournalDTO;
import ru.ivanov.Publisher.models.Article;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.repositories.ArticleRepository;

import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final JournalService journalService;
    private final ModelMapper modelMapper;

    @Transactional
    public ArticleDTO create(ArticleDTO articleDTO) {
        Article article = convertToEntity(articleDTO);
        if (thereIsNoArticleWithSameId(article)) {
            return convertToDTO(articleRepository.save(article));
        } else {
            throw new IllegalArgumentException("There is already article with such id");
        }
    }

    public ArticleDTO readById(int id) {
        Article foundArticle = articleRepository.findById(id).
                orElseThrow(() ->
                        new IllegalArgumentException("There is no article with such id"));
        return convertToDTO(foundArticle);
    }

    public List<ArticleDTO> readAll() {
        return articleRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public List<ArticleDTO> readAllByJournal(int journalId) {
        Journal journal = journalService.getJournalById(journalId);
        return articleRepository.findByJournal(journal).stream().map(this::convertToDTO).toList();
    }

    @Transactional
    public ArticleDTO update(ArticleDTO articleDTO) {
        Article article = convertToEntity(articleDTO);
        if (thereIsArticleWithSameId(article)) {
            return convertToDTO(articleRepository.save(article));
        } else {
            throw new IllegalArgumentException("There is no article with such id");
        }
    }

    @Transactional
    public void delete(int id) {
        articleRepository.deleteById(id);
    }

    private boolean thereIsNoArticleWithSameId(Article article) {
        return !thereIsArticleWithSameId(article);
    }

    private boolean thereIsArticleWithSameId(Article article) {
        int articleId = article.getId();
        return articleRepository.findById(articleId).isPresent();
    }

    private Article convertToEntity(ArticleDTO articleDTO) {
        return modelMapper.map(articleDTO, Article.class);
    }

    private ArticleDTO convertToDTO(Article article) {
        return modelMapper.map(article, ArticleDTO.class);
    }
}
