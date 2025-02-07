package ru.ivanov.Publisher.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.Publisher.dto.ArticleDTO;
import ru.ivanov.Publisher.dto.validationGroups.OnCreate;
import ru.ivanov.Publisher.dto.validationGroups.OnUpdate;
import ru.ivanov.Publisher.services.ArticleService;

import java.util.List;


/**
 * @author Ivan Ivanov
 **/
@RestController
@Tag(name = "Контролер статей журнала", description = "Контроллер для работы со статьями журнала")
@RequiredArgsConstructor
@RequestMapping("/publisher/{journalId}")
@Validated
public class JournalController {
    private final ArticleService articleService;

    @Operation(
            summary = "Показать статьи",
            description = "Показывает статьи журнала"
    )
    @GetMapping(produces = "application/json")
    public List<ArticleDTO> showArticles(@PathVariable int journalId) {
        return articleService.readAllByJournal(journalId);
    }

    @Operation(
            summary = "Создать статью",
            description = "Создает новую статью в журнале"
    )
    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    public ArticleDTO createArticle(@RequestBody @Valid ArticleDTO newArticle,
                                    @PathVariable int journalId) {
        newArticle.setId(journalId);
        return articleService.create(newArticle);
    }

    @Operation(
            summary = "Изменить статью",
            description = "Изменяет существующую статьи журнала"
    )
    @PutMapping(path = "/{articleId}/edit", produces = "application/json")
    @Validated(OnUpdate.class)
    public ArticleDTO updateArticle(@RequestBody @Valid ArticleDTO articleToUpdate,
                                           @PathVariable int journalId) {
        articleToUpdate.setId(journalId);
        return articleService.update(articleToUpdate);
    }

    @Operation(
            summary = "Удалить статьи",
            description = "Удаляет статью журнала"
    )
    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable("articleId") @Parameter(description = "Идентификатор удаляемой статьи", example = "1")
                                           int articleId) {
        articleService.delete(articleId);
    }
}
