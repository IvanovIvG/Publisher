package ru.ivanov.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivanov.backend.stagesEnums.FourStateStage;
import ru.ivanov.backend.stagesEnums.ThreeStateStage;
import ru.ivanov.backend.stagesEnums.TwoStateStage;
import ru.ivanov.backend.dto.validationGroups.OnCreate;

import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Сущность статьи")
@Data
@NoArgsConstructor
public class ArticleDTO {
    @Schema(description = "Уникальный идентификатор статьи", example = "01950a12-e683-7d10-9c45-ee66c4b4aaad")
    private UUID id;

    @Schema(description = "Название статьи", example = "article1")
    @JsonProperty("название")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    @NotBlank(message = "Name must not be empty", groups = OnCreate.class)
    private String name;

    @Schema(description = "Тема статьи", example = "topic1")
    @JsonProperty("тема")
    @Size(min = 1, max = 100, message = "Topic must be from 1 to 100 length")
    @NotBlank(message = "Topic must not be empty", groups = OnCreate.class)
    private String topic;

    @Schema(description = "Число страниц в статье", example = "1")
    @JsonProperty("страниц")
    @Min(value = 0, message = "Number of pages must be positive")
    private int pages;

    @Schema(description = "Номер первой страницы статьи", example = "1")
    @JsonProperty("первая страница")
    @Min(value = 0, message = "Number of first page must be positive")
    private int firstPage;

    @Schema(description = "Номер последней страницы статьи", example = "2")
    @JsonProperty("последняя страница")
    @Min(value = 0, message = "Number of last page must be positive")
    private int lastPage;

    @Schema(description = "Статус этапа просмотра главным редактором", example = "Not_Ready_And_Ordered")
    @JsonProperty("главный редактор")
    private FourStateStage chiefEditor;

    @Schema(description = "Статус этапа просмотра научным редактором", example = "Not_Ready")
    @JsonProperty("научные редактор")
    private ThreeStateStage scienceEditor;

    @Schema(description = "Статус этапа согласование с автором", example = "Not_Ready")
    @JsonProperty("согласование с автором")
    private ThreeStateStage authorCoordination;

    @Schema(description = "Статус этапа корректура", example = "Not_Ready")
    @JsonProperty("корректура")
    private ThreeStateStage corrector;

    @Schema(description = "Статус этапа верстка", example = "Not_Ready")
    @JsonProperty("верстка")
    private TwoStateStage pageProofs;

    @Schema(description = "Статус этапа макет", example = "Not_Ready")
    @JsonProperty("макет")
    private TwoStateStage dummy;
}
