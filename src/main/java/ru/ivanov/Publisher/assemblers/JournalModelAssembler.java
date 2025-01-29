package ru.ivanov.Publisher.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.ivanov.Publisher.controllers.JournalController;
import ru.ivanov.Publisher.controllers.PublisherController;
import ru.ivanov.Publisher.models.Journal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * @author Ivan Ivanov
 **/
@Component
public class JournalModelAssembler implements RepresentationModelAssembler<Journal, EntityModel<Journal>> {
    @Override
    public EntityModel<Journal> toModel(Journal journal) {
        int journalId = journal.getId();
        return EntityModel.of(journal,
                linkTo(methodOn(JournalController.class).showArticles(journalId)).withSelfRel(),
                linkTo(methodOn(PublisherController.class).showAllJournals()).withRel("allJournals"));
    }
}
