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
@Schema(description = "Сущность пользователя")
@Data
@UniqueUsername
public class UserDTO {
    @Schema(description = "Уникальный идентификатор пользователя", example = "01950a12-e683-7d10-9c45-ee66c4b4aaad")
    private UUID id;

    @Schema(description = "Уникальный логин пользователя", example = "User")
    @JsonProperty("логин")
    @Size(min = 1, max = 45, message = "Username must be from 1 to 45 length")
    @NotBlank(message = "Name must not be empty")
    private String username;

    @Schema(description = "Права пользователя", example = "ROLE_ADMIN")
    private Role role;
}
