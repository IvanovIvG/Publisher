package ru.ivanov.Publisher.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import ru.ivanov.Publisher.dto.JournalDTO;
import ru.ivanov.Publisher.dto.NotFoundError;
import ru.ivanov.Publisher.dto.ValidationError;
import ru.ivanov.Publisher.dto.validationGroups.OnCreate;
import ru.ivanov.Publisher.dto.validationGroups.OnUpdate;
import ru.ivanov.Publisher.services.JournalService;

import java.util.List;
import java.util.UUID;


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
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    array =
                                    @ArraySchema(
                                            schema = @Schema(implementation = JournalDTO.class))),
                            description = "Найдены все журналы"
                    )
            }
    )
    @GetMapping(produces = "application/json")
    public List<JournalDTO> showAllJournals() {
        return journalService.readAll();
    }


    @Operation(
            summary = "Создать журнал",
            description = "Создает новый журнал"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = JournalDTO.class)),
                            description = "Журнал создан"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationError.class)),
                            description = "Ошибка валидации"
                    )
            }
    )
    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    public JournalDTO createJournal(@RequestBody @Valid JournalDTO newJournal) {
        newJournal.setId(null);
        return journalService.create(newJournal);
    }


    @Operation(
            summary = "Изменить журнал",
            description = "Изменяет существующий журнал"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = JournalDTO.class)),
                            description = "Журнал обновлен"
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
                                    schema = @Schema(
                                            implementation = NotFoundError.class)),
                            description = "Журнал не найден"
                    )
            }
    )
    @PutMapping(path = "/{journalId}", produces = "application/json")
    @Validated(OnUpdate.class)
    public JournalDTO updateJournal(@RequestBody @Valid JournalDTO journalToUpdate,
                                    @Parameter(description = "id изменяемого журнала",
                                            example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
                                    @PathVariable UUID journalId) {
        journalToUpdate.setId(journalId);
        return journalService.update(journalToUpdate);
    }


    @Operation(
            summary = "Удалить журнал",
            description = "Удаляет существующий журнал"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Журнал удален"
                    )
            }
    )
    @DeleteMapping("/{journalId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteJournal(@PathVariable("journalId")
                              @Parameter(description = "id удаляемого журнала",
                                      example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
                              UUID journalId) {
        journalService.delete(journalId);
    }
}
