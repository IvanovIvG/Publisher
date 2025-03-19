package ru.ivanov.securityserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.securityserver.dto.PasswordDTO;
import ru.ivanov.securityserver.dto.UserCreationDTO;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.dto.errors.NotFoundError;
import ru.ivanov.securityserver.dto.errors.ValidationError;
import ru.ivanov.securityserver.services.UserService;

import java.util.List;
import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@RestController
@Tag(name = "Контролер пользователей приложения", description = "Контроллер для работы с пользователями приложения")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Показать пользователя",
            description = "Показывает информацию о пользователе приложения"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserDTO.class)),
                            description = "Найден пользователь"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = NotFoundError.class)),
                            description = "Пользователь не найден"
                    )
            }
    )
    @GetMapping(produces = "application/json")
    @SecurityRequirement(name = "JWT")
    public UserDTO showUser(Authentication authentication) {
        String username = authentication.getName();
        return userService.readByUserName(username);
    }


    @Operation(
            summary = "Изменить пароль",
            description = "Меняет пароль аутентифицированного пользователя"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пароль пользователя изменен"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationError.class)),
                            description = "Ошибка валидации"
                    )
            }
    )
    @PutMapping(path = "/password")
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody
                               @Valid
                               PasswordDTO passwordDTO) {
        userService.changePassword(passwordDTO);
    }
}
