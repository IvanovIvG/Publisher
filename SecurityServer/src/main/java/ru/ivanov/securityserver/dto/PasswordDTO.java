package ru.ivanov.securityserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Данные для смены пароля")
@Data
public class PasswordDTO {
    @Schema(description = "Старый пароль", example = "oldPassword")
    @NotBlank(message = "Name must not be empty")
    private String oldPassword;

    @Schema(description = "Новый пароль", example = "newPassword")
    @NotBlank(message = "Name must not be empty")
    private String newPassword;
}
