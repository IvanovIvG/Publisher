package ru.ivanov.securityserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Права доступа пользователя", example = "ROLE_ADMIN")
public enum Role {
    ROLE_READ, ROLE_ADMIN
}
