package ru.ivanov.securityserver.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import ru.ivanov.securityserver.dto.Role;
import ru.ivanov.securityserver.dto.UserDTO;
import ru.ivanov.securityserver.dto.UserInfo;

import java.util.List;
import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Service
public class UserService {
    private final JdbcUserDetailsManager userDetailsManager;

    public UserService(UserDetailsService userDetailsManager) {
        this.userDetailsManager = (JdbcUserDetailsManager) userDetailsManager;
    }

    public List<UserDTO> readAll() {
        return null;
    }

    public UserDTO readById(UUID userId) {
        return null;
    }

    public UserDTO create(UserInfo newUser) {
        return null;
    }

    public UserDTO update(UserInfo updatedUser) {
        return null;
    }

    public void deleteUser(UUID userId){
    }

    public UserDTO changeRole(UUID userId, Role newRole) {
        return null;
    }

    public void changePassword(UUID userId, String newPassword) {
    }
}
