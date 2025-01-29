package ru.ivanov.Publisher.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.Publisher.assemblers.JournalModelAssembler;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.services.JournalService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Ivan Ivanov
 **/
@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final JournalService journalService;
    private final JournalModelAssembler assembler;

    @Autowired
    public PublisherController(JournalService journalService, JournalModelAssembler assembler) {
        this.journalService = journalService;
        this.assembler = assembler;
    }

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
    public ResponseEntity<?> createJournal(@RequestBody Journal newJournal) {
        Journal createdJournal = journalService.create(newJournal);
        EntityModel<Journal> journalEntityModel = assembler.toModel(createdJournal);
        return new ResponseEntity<>(journalEntityModel, HttpStatus.CREATED);
    }

    @PatchMapping("/{journalId}/edit")
    public ResponseEntity<?> updateJournal(@PathVariable("journalId") int journalId,
                                    @RequestBody Journal journalToUpdate) {
        journalToUpdate.setId(journalId);
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
