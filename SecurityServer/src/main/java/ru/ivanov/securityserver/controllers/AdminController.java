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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.securityserver.dto.Role;
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
@RequiredArgsConstructor
@Tag(name = "Контролер администратора", description = "Контроллер администратора для управления пользователями приложения")
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Operation(
            summary = "Показать всех пользователей",
            description = "Показывает пользователей приложения"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserDTO.class)),
                            description = "Найдены все пользователи приложения"
                    )
            }
    )
    @GetMapping(produces = "application/json")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> showAllUsers() {
        return userService.readAll();
    }

    @Operation(
            summary = "Показать пользователя",
            description = "Показывает пользователя приложения"
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
    @GetMapping(path = "/{userId}", produces = "application/json")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO showUser(@PathVariable
                            @Parameter(description = "id пользователя",
                                    example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
                            UUID userId) {
        return userService.readById(userId);
    }

    @Operation(
            summary = "Создать пользователя",
            description = "Создает нового пользователя"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = UserDTO.class)),
                            description = "Пользователь создан"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationError.class)),
                            description = "Ошибка валидации"
                    )
            }
    )
    @PostMapping(produces = "application/json")
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO createUser(@RequestBody @Valid UserCreationDTO userCreationDTO) {
        UserDTO newUser = userCreationDTO.getUserDTO();
        newUser.setId(null);
        newUser.setRole(new Role("ROLE_USER"));
        String password = userCreationDTO.getPassword();
        return userService.create(newUser, password);
    }

    @Operation(
            summary = "Изменить права доступа",
            description = "Меняет права доступа пользователя"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserDTO.class)),
                            description = "Права доступа пользователя изменены"
                    )
            }
    )
    @PutMapping(path = "/{userId}/role")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO changeRole(@PathVariable
                              @Parameter(description = "id пользователя",
                                      example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
                              UUID userId,
                              @RequestBody
                              @Schema(description = "Новое право доступа пользователя",
                                      example = "ROLE_USER"
                              )
                              String newRoleString) {
        newRoleString = newRoleString.replaceAll("[^A-Za-z_0-9]", "");
        GrantedAuthority newRole = new SimpleGrantedAuthority(newRoleString);
        return userService.changeRole(userId, newRole);
    }

    @Operation(
            summary = "Удалить пользавателя",
            description = "Удаляет пользавателя"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь удален"
                    )
            }
    )
    @DeleteMapping(path = "/{userId}")
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable
                           @Parameter(description = "id пользователя",
                                   example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
                           UUID userId) {
        userService.deleteUser(userId);
    }
}
