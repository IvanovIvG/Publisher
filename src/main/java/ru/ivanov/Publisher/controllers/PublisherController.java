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
import ru.ivanov.Publisher.dto.JournalDTO;
import ru.ivanov.Publisher.dto.validationGroups.OnCreate;
import ru.ivanov.Publisher.dto.validationGroups.OnUpdate;
import ru.ivanov.Publisher.services.JournalService;

import java.util.List;


/**
 * @author Ivan Ivanov
 **/
@Tag(name = "Контролер журналов", description = "Контроллер для работы с журналами издательства")
@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
@Validated
public class PublisherController {
    private final JournalService journalService;

    @Operation(
            summary = "Показать журналы",
            description = "Показывает все журналы издательства"
    )
    @GetMapping(produces = "application/json")
    public List<JournalDTO> showAllJournals(){
        return journalService.readAll();
    }

    @Operation(
            summary = "Создать журнал",
            description = "Создает новый журнал"
    )
    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    public JournalDTO createJournal(@RequestBody @Valid JournalDTO newJournal) {
        newJournal.setId(0);
        return journalService.create(newJournal);
    }

    @Operation(
            summary = "Изменить журнал",
            description = "Изменяет существующий журнал"
    )
    @PutMapping(path="/{journalId}/edit", produces = "application/json")
    @Validated(OnUpdate.class)
    public JournalDTO updateJournal(@RequestBody @Valid JournalDTO journalToUpdate, @PathVariable int journalId) {
        journalToUpdate.setId(journalId);
        return journalService.update(journalToUpdate);
    }

    @Operation(
            summary = "Удалить журнал",
            description = "Удаляет существующий журнал"
    )
    @DeleteMapping("/{journalId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteJournal(@PathVariable("journalId") @Parameter(description = "Идентификатор удаляемого журнала", example = "1")
                                               int journalId) {
        journalService.delete(journalId);
    }
}
