package ru.ivanov.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Ошибка сушность не найдена")
@RequiredArgsConstructor
@Getter
public class NotFoundError {
    @Schema(description = "Сообщение об ошибке", example = "There is no entity with such id")
    private final String message;
}