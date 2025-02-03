package ru.ivanov.Publisher.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.Publisher.assemblers.JournalModelAssembler;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.models.validationGroups.OnCreate;
import ru.ivanov.Publisher.models.validationGroups.OnUpdate;
import ru.ivanov.Publisher.services.JournalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Ivan Ivanov
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
@Validated
public class PublisherController {
    private final JournalService journalService;
    private final JournalModelAssembler assembler;

    @GetMapping()
    public ResponseEntity<?> showAllJournals(){
        List<EntityModel<Journal>> journals = journalService.readAll().stream()
                .map(assembler::toModel)
                .toList();

        CollectionModel<EntityModel<Journal>> journalCollectionModel = CollectionModel.of(journals,
                linkTo(methodOn(PublisherController.class).showAllJournals()).withSelfRel());

        return ResponseEntity.ok(journalCollectionModel);
    }

    @PostMapping()
    @Validated(OnCreate.class)
    public ResponseEntity<?> createJournal(@RequestBody @Valid Journal newJournal) {
        Journal createdJournal = journalService.create(newJournal);
        EntityModel<Journal> journalEntityModel = assembler.toModel(createdJournal);
        return new ResponseEntity<>(journalEntityModel, HttpStatus.CREATED);
    }

    @PatchMapping("/{journalId}/edit")
    @Validated(OnUpdate.class)
    public ResponseEntity<?> updateJournal(@RequestBody @Valid Journal journalToUpdate) {
        Journal updatedJournal = journalService.update(journalToUpdate);
        EntityModel<Journal> journalEntityModel = assembler.toModel(updatedJournal);
        return ResponseEntity.ok(journalEntityModel);
    }

    @DeleteMapping("/{journalId}")
    public ResponseEntity<?> deleteJournal(@PathVariable("journalId") int journalId) {
        journalService.delete(journalId);
        return ResponseEntity.ok().build();
    }
}
