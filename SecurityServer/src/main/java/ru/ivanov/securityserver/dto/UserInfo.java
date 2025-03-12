package ru.ivanov.securityserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Данные пользователя не влияющие на работу приложения")
@Data
public class UserInfo {
    @Schema(description = "Уникальный идентификатор пользователя", example = "01950a12-e683-7d10-9c45-ee66c4b4aaad")
    private UUID id;

    @Schema(description = "Логин пользователя", example = "User")
    @JsonProperty("логин")
    @Size(min = 1, max = 100, message = "Username must be from 1 to 100 length")
    @NotBlank(message = "Name must not be empty")
    private String username;
}
