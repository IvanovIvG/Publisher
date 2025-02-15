package ru.ivanov.Publisher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivanov.Publisher.dto.validationGroups.OnCreate;

import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Сущность журнала")
@Data
@NoArgsConstructor
public class JournalDTO {
    @Schema(description = "Уникальный идентификатор журнала", example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
    private UUID id;

    @Schema(description = "Название журнала", example = "journal1")
    @JsonProperty("название")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    @NotBlank(message = "Name must not be empty", groups = OnCreate.class)
    private String journalName;

    @Schema(description = "Номер журнала", example = "01/25")
    @JsonProperty("номер")
    @Pattern(regexp = "^\\d{2,}/\\d{2,}$", message = "Number must be in 01/25 format")
    @NotBlank(message = "Name must not be empty", groups = OnCreate.class)
    private String journalNumber;

    @Schema(description = "Должно ли поле id статьи показываться", example = "true")
    private boolean articleIdToShow = false;

    @Schema(description = "Должно ли поле name статьи показываться", example = "true")
    private boolean articleNameToShow = true;

    @Schema(description = "Должно ли поле topic статьи показываться", example = "true")
    private boolean articleTopicToShow = true;

    @Schema(description = "Должно ли поле pages статьи показываться", example = "true")
    private boolean articlePagesToShow = true;

    @Schema(description = "Должно ли поле firstPage статьи показываться", example = "true")
    private boolean articleFirstPageToShow = true;

    @Schema(description = "Должно ли поле lastPage статьи показываться", example = "true")
    private boolean articleLastPageToShow = true;

    @Schema(description = "Должно ли поле chiefEditor статьи показываться", example = "true")
    private boolean articleChiefEditorToShow = true;

    @Schema(description = "Должно ли поле статьи scienceEditor показываться", example = "true")
    private boolean articleScienceEditorToShow = true;

    @Schema(description = "Должно ли поле статьи authorCoordination показываться", example = "true")
    private boolean articleAuthorCoordinationToShow = true;

    @Schema(description = "Должно ли поле corrector статьи показываться", example = "true")
    private boolean articleCorrectorToShow = true;

    @Schema(description = "Должно ли поле pageProofs статьи показываться", example = "true")
    private boolean articlePageProofsToShow = true;

    @Schema(description = "Должно ли поле dummy статьи показываться", example = "true")
    private boolean articleDummyToShow = true;
}
