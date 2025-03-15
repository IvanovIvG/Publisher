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
    public ArticleDTO create(ArticleDTO articleDTO, UUID journalId) {
        Article article = convertToEntity(articleDTO, journalId);
        article.setId(generateArticleUniqueID());
        article = articleRepository.save(article);
        return convertToDTO(article);
    }

    @Transactional
    public ArticleDTO update(ArticleDTO articleDTO, UUID journalId) {
        Article article = convertToEntity(articleDTO, journalId);
        if (articleExists(article)) {
            article = articleRepository.save(article);
        } else {
            throw new IllegalArgumentException("There is no article with such id");
        }
        return convertToDTO(article);
    }


    @Transactional
    public void delete(UUID id) {
        articleRepository.deleteById(id);
    }

    private UUID generateArticleUniqueID() {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        while (suchArticleIDIsNotUnique(id)) {
            id = Generators.timeBasedEpochGenerator().generate();
        }
        return id;
    }

    private boolean suchArticleIDIsNotUnique(UUID id) {
        return articleRepository.findById(id).isPresent();
    }

    private boolean articleExists(Article article){
        return articleRepository.existsById(article.getId());
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
