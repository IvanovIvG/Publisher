package ru.ivanov.securityserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Data
public class UserInfo {
    private UUID id;

    @NotBlank(message = "Name must not be empty")
    private String name;
}
