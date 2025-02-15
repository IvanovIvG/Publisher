package ru.ivanov.Publisher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
    private String name;
}
