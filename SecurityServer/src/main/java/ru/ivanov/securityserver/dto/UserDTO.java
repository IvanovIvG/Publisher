package ru.ivanov.securityserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Сущность пользователя")
@Data
public class UserDTO {
    @Schema(description = "Уникальный идентификатор пользователя")
    @Valid
    private UserInfoDTO userInfoDTO;

    @Schema(description = "Права пользователя", example = "ROLE_ADMIN")
    private Role role;
}
