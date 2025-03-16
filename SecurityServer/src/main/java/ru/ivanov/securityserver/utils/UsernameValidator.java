package ru.ivanov.securityserver.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.ivanov.securityserver.dto.UniqueUsername;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<UniqueUsername, UserDTO> {
    private final UserService userService;

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext constraintValidatorContext) {
        String username = user.getUsername();
        Optional<UserDTO> userWithSameUsername = getUserWithSameUsername(username);
        if (userWithSameUsername.isEmpty()) {
            return true;
        }

        UserDTO sameUsernameUser = userWithSameUsername.get();
        return theyHaveSameId(user, sameUsernameUser);
    }

    private Optional<UserDTO> getUserWithSameUsername(String username) {
        List<UserDTO> allUsers = userService.readAll();
        return allUsers.stream().filter(user -> user.getUsername().equals(username)).findAny();
    }

    private boolean theyHaveSameId(UserDTO user, UserDTO userWithSameUsername) {
        UUID userId = user.getId();
        UUID userWithSameUsernameId = userWithSameUsername.getId();
        return userId.equals(userWithSameUsernameId);
    }
}


