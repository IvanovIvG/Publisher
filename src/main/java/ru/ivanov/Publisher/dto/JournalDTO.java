package ru.ivanov.Publisher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivanov.Publisher.dto.validationGroups.OnCreate;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Сущность журнала")
@Data
@NoArgsConstructor
public class JournalDTO {
    @Schema(description = "Уникальный идентификатор журнала", example = "1")
    @Min(value = 0, message = "Id must be positive")
    private int id;

    @Schema(description = "Название журнала", example = "journal1")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    @NotBlank(message = "Name must not be empty", groups = OnCreate.class)
    private String name;
}
