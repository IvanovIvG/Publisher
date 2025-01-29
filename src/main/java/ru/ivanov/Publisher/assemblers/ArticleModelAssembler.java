package ru.ivanov.Publisher.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.ivanov.Publisher.controllers.JournalController;
import ru.ivanov.Publisher.controllers.PublisherController;
import ru.ivanov.Publisher.models.Article;
import ru.ivanov.Publisher.models.Journal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Ivan Ivanov
 **/
@Component
public class ArticleModelAssembler implements RepresentationModelAssembler<Article, EntityModel<Article>> {
    @Override
    public EntityModel<Article> toModel(Article article) {
        int journalId = article.getJournal().getId();
        return EntityModel.of(article,
                linkTo(methodOn(JournalController.class).showArticles(journalId)).withRel("allJournalArticles"));
    }
}
