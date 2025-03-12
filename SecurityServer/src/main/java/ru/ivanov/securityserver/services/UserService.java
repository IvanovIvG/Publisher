package ru.ivanov.securityserver.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

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


    public void deleteUser(UUID userId){
        //userDetailsManager.deleteUser();
    }
}
