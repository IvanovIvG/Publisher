package ru.ivanov.Publisher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivanov.Publisher.stagesEnums.FourStateStage;
import ru.ivanov.Publisher.stagesEnums.ThreeStateStage;
import ru.ivanov.Publisher.stagesEnums.TwoStateStage;
import ru.ivanov.Publisher.dto.validationGroups.OnCreate;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Сущность статьи")
@Data
@NoArgsConstructor
public class ArticleDTO {
    @Schema(description = "Уникальный идентификатор статьи", example = "1")
    @Min(value = 0, message = "Id must be positive")
    private int id;

    @Schema(description = "Название статьи", example = "article1")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    @NotBlank(message = "Name must not be empty", groups = OnCreate.class)
    private String name;

    @Schema(description = "Тема статьи", example = "topic1")
    @Size(min = 1, max = 100, message = "Topic must be from 1 to 100 length")
    @NotBlank(message = "Topic must not be empty", groups = OnCreate.class)
    private String topic;

    @Schema(description = "Число страниц в статье", example = "1")
    @Min(value = 0, message = "Number of pages must be positive")
    private int pages;

    @Schema(description = "Номер первой страницы статьи", example = "1")
    @Min(value = 0, message = "Number of first page must be positive")
    private int firstPage;

    @Schema(description = "Номер последней страницы статьи", example = "2")
    @Min(value = 0, message = "Number of last page must be positive")
    private int lastPage;

    @Schema(description = "Статус этапа просмотра главным редактором", example = "Not_Ready_And_Ordered")
    private FourStateStage chiefEditor;

    @Schema(description = "Статус этапа просмотра научным редактором", example = "Not_Ready")
    private ThreeStateStage scienceEditor;

    @Schema(description = "Статус этапа согласование с автором", example = "Not_Ready")
    private ThreeStateStage authorCoordination;

    @Schema(description = "Статус этапа корректура", example = "Not_Ready")
    private ThreeStateStage corrector;

    @Schema(description = "Статус этапа верстка", example = "Not_Ready")
    private TwoStateStage pageProofs;

    @Schema(description = "Статус этапа макет", example = "Not_Ready")
    private TwoStateStage dummy;
}
