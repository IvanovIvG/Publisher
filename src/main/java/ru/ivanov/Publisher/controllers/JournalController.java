package ru.ivanov.Publisher.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.Publisher.assemblers.ArticleModelAssembler;
import ru.ivanov.Publisher.models.Article;
import ru.ivanov.Publisher.models.validationGroups.OnCreate;
import ru.ivanov.Publisher.models.validationGroups.OnUpdate;
import ru.ivanov.Publisher.services.ArticleService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Ivan Ivanov
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/publisher/{journalId}")
@Validated
public class JournalController {
    private final ArticleService articleService;
    private final ArticleModelAssembler assembler;

    @GetMapping()
    public ResponseEntity<?> showArticles(@PathVariable int journalId){
        List<EntityModel<Article>> articles = articleService.readAllByJournal(journalId).stream()
                .map(assembler::toModel)
                .toList();

        CollectionModel<EntityModel<Article>> articleCollectionModel = CollectionModel.of(articles,
                linkTo(methodOn(JournalController.class).showArticles(journalId)).withSelfRel());

        return ResponseEntity.ok(articleCollectionModel);
    }

    @PostMapping()
    @Validated(OnCreate.class)
    public ResponseEntity<?> createArticle(@RequestBody @Valid Article newArticle) {
        Article createdArticle = articleService.create(newArticle);
        EntityModel<Article> articleEntityModel = assembler.toModel(createdArticle);
        return new ResponseEntity<>(articleEntityModel, HttpStatus.CREATED);
    }

    @PatchMapping("/{articleId}/edit")
    @Validated(OnUpdate.class)
    public ResponseEntity<?> updateArticle(@RequestBody @Valid Article articleToUpdate) {
        Article updatedArticle = articleService.update(articleToUpdate);
        EntityModel<Article> articleEntityModel = assembler.toModel(updatedArticle);
        return ResponseEntity.ok(articleEntityModel);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable("articleId") int articleId) {
        articleService.delete(articleId);
        return ResponseEntity.ok().build();
    }
}
