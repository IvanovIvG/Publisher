package ru.ivanov.Publisher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Ошибка при валидации")
@RequiredArgsConstructor
@Getter
public class ValidationError {
    @Schema(description = "Поле которое не прошло валидацию", example = "name")
    private final String fieldName;

    @Schema(description = "Сообщение об ошибке", example = "Name must not be empty")
    private final String message;

}