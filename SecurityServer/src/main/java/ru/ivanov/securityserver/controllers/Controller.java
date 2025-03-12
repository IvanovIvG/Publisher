package ru.ivanov.securityserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivan Ivanov
 **/
@RestController
@RequestMapping("/security")
public class Controller {
    private final JdbcUserDetailsManager userDetailsManager;

    public Controller(UserDetailsService userDetailsManager) {
        this.userDetailsManager = (JdbcUserDetailsManager) userDetailsManager;
    }


    @GetMapping()
    public Boolean showUserExist () {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return true;
    }
}
