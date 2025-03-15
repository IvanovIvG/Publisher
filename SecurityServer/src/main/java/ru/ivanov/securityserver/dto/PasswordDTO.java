package ru.ivanov.securityserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Ivan Ivanov
 **/
@Data
public class PasswordDTO {
    @NotBlank(message = "Name must not be empty")
    private String oldPassword;

    @NotBlank(message = "Name must not be empty")
    private String newPassword;
}
