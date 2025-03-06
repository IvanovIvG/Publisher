package ru.ivanov.backend.services;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.backend.dto.ArticleDTO;
import ru.ivanov.backend.models.Article;
import ru.ivanov.backend.models.Journal;
import ru.ivanov.backend.repositories.ArticleRepository;

import java.util.List;
import java.util.UUID;

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
    public ArticleDTO create(ArticleDTO articleDTO, UUID journalId) {
        Article article = convertToEntity(articleDTO, journalId);
        article.setId(Generators.timeBasedEpochGenerator().generate());
        if (thereIsNoArticleWithSameId(article)) {
            return convertToDTO(articleRepository.save(article));
        } else {
            throw new IllegalArgumentException("There is already article with such id");
        }
    }

    public ArticleDTO readById(UUID id) {
        Article foundArticle = articleRepository.findById(id).
                orElseThrow(() ->
                        new IllegalArgumentException("There is no article with such id"));
        return convertToDTO(foundArticle);
    }

    public List<ArticleDTO> readAll() {
        return articleRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public List<ArticleDTO> readAllByJournal(UUID journalId) {
        Journal journal = journalService.getJournalById(journalId);
        return articleRepository.findByJournal(journal).stream().map(this::convertToDTO).toList();
    }

    @Transactional
    public ArticleDTO update(ArticleDTO articleDTO, UUID journalId) {
        Article article = convertToEntity(articleDTO, journalId);
        if (thereIsArticleWithSameId(article)) {
            return convertToDTO(articleRepository.save(article));
        } else {
            throw new IllegalArgumentException("There is no article with such id");
        }
    }

    @Transactional
    public void delete(UUID id) {
        articleRepository.deleteById(id);
    }

    private boolean thereIsNoArticleWithSameId(Article article) {
        return !thereIsArticleWithSameId(article);
    }

    private boolean thereIsArticleWithSameId(Article article) {
        UUID articleId = article.getId();
        return articleRepository.findById(articleId).isPresent();
    }

    private Article convertToEntity(ArticleDTO articleDTO, UUID journalId) {
        Article article = modelMapper.map(articleDTO, Article.class);
        article.setJournal(journalService.getJournalById(journalId));
        return article;
    }

    private ArticleDTO convertToDTO(Article article) {
        return modelMapper.map(article, ArticleDTO.class);
    }
}
