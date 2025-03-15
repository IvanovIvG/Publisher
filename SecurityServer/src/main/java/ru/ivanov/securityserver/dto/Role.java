package ru.ivanov.securityserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Ivan Ivanov
 **/
@Schema(description = "Права доступа пользователя", example = "ROLE_ADMIN")
public enum Role implements GrantedAuthority {
    ROLE_READ, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
