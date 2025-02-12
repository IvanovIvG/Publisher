package ru.ivanov.Publisher.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.Publisher.dto.ArticleDTO;
import ru.ivanov.Publisher.dto.NotFoundError;
import ru.ivanov.Publisher.dto.ValidationError;
import ru.ivanov.Publisher.dto.validationGroups.OnCreate;
import ru.ivanov.Publisher.dto.validationGroups.OnUpdate;
import ru.ivanov.Publisher.services.ArticleService;
import ru.ivanov.Publisher.services.JournalService;

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
    private final JournalService journalService;

    @Operation(
            summary = "Показать статьи",
            description = "Показывает статьи журнала"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = ArticleDTO.class)),
                            description = "Найдены все статьи журнала"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = NotFoundError.class)),
                            description = "Статьи не найдены"
                    )
            }
    )
    @GetMapping(produces = "application/json")
    public List<ArticleDTO> showArticles(@PathVariable
                                         @Parameter(description = "id журнала статей", example = "1")
                                         int journalId) {
        return articleService.readAllByJournal(journalId);
    }


    @Operation(
            summary = "Создать статью",
            description = "Создает новую статью в журнале"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = ArticleDTO.class)),
                            description = "Статья создана"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationError.class)),
                            description = "Ошибка валидации"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(implementation = NotFoundError.class)),
                            description = "Не найден журнал статьи"
                    )
            }
    )
    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    public ArticleDTO createArticle(@RequestBody @Valid ArticleDTO newArticle,
                                    @PathVariable
                                    @Parameter(description = "id журнала создаваемой статьи", example = "1")
                                    int journalId) {
        newArticle.setId(0);
        newArticle.setJournal(journalService.readById(journalId));
        return articleService.create(newArticle);
    }


    @Operation(
            summary = "Изменить статью",
            description = "Изменяет существующую статьи журнала"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = ArticleDTO.class)),
                            description = "Статья изменена"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationError.class)),
                            description = "Ошибка валидации"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(implementation = NotFoundError.class)),
                            description = "Не найден журнал или статья"
                    )
            }
    )
    @PutMapping(path = "/{articleId}", produces = "application/json")
    @Validated(OnUpdate.class)
    public ArticleDTO updateArticle(@RequestBody @Valid ArticleDTO articleToUpdate,
                                    @PathVariable
                                    @Parameter(description = "id журнала изменяемой статьи", example = "1")
                                    int journalId,
                                    @PathVariable
                                    @Parameter(description = "id создаваемой статьи", example = "1")
                                    int articleId) {
        articleToUpdate.setId(articleId);
        articleToUpdate.setJournal(journalService.readById(journalId));
        return articleService.update(articleToUpdate);
    }


    @Operation(
            summary = "Удалить статьи",
            description = "Удаляет статью журнала"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Статья удалена"
                    )
            }
    )
    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable
                              @Parameter(description = "id журнала удаляемой статьи", example = "1")
                              int journalId,
                              @PathVariable("articleId")
                              @Parameter(description = "id удаляемой статьи", example = "1")
                              int articleId) {
        articleService.delete(articleId);
    }
}
