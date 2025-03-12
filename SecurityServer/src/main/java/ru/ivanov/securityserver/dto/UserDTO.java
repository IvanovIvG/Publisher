package ru.ivanov.securityserver.dto;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * @author Ivan Ivanov
 **/
@Data
public class UserDTO {
    @Valid
    private UserInfo userInfo;

    private String role;
}
