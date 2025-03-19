package ru.ivanov.securityserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Данные для создания пользователя")
@Data
public class UserCreationDTO {

    @Schema(description = "Данные пользователя")
    @Valid
    private UserDTO userDTO;

    @Schema(description = "Пароль пользователя", example = "qwerty")
    @NotBlank(message = "Password must not be empty")
    private String password;
}
