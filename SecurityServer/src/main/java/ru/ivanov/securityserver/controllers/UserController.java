package ru.ivanov.securityserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.securityserver.dto.Role;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.services.UserService;
import ru.ivanov.securityserver.valiodators.PasswordValidator;

import java.util.List;
import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@RestController
@Tag(name = "Контролер пользователей приложения", description = "Контроллер для работы с пользователями приложения")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordValidator passwordValidator;


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
                    )
            }
    )
    @GetMapping(path = "/{userId}", produces = "application/json")
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
                    )
            }
    )
    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody @Valid UserDTO newUser) {
        newUser.setId(null);
        newUser.setRole(Role.ROLE_READ);
        String password = "password";
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
    public UserDTO changeRole(@PathVariable
                              @Parameter(description = "id пользователя",
                                      example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
                              UUID userId,
                              @RequestBody
                              @Schema(description = "Новое право доступа пользователя",
                                      example = "ROLE_READ"
                              )
                              Role newRole) {
        return userService.changeRole(userId, newRole);
    }


    @Operation(
            summary = "Изменить пароль",
            description = "Меняет пароль пользователя"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пароль пользователя изменен"
                    )
            }
    )
    @PutMapping(path = "/{userId}/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@PathVariable
                               @Parameter(description = "id пользователя",
                                       example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
                               UUID userId,
                               @RequestBody
                               @Schema(description = "новый пароль",
                                       example = "newSuperSecretPassword")
                               String newPassword) {
        passwordValidator.validate(newPassword);
        userService.changePassword(userId, newPassword);
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
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable
                           @Parameter(description = "id пользователя",
                                   example = "01950a0f-e717-7193-8e4c-fa9baedd9874")
                           UUID userId) {
        userService.deleteUser(userId);
    }
}
