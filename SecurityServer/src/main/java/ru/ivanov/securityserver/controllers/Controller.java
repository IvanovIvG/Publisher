package ru.ivanov.securityserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@RestController
@RequestMapping("/security")
public class Controller {

    @GetMapping()
    public Boolean showUserExist () {
        System.out.println("in controller");
        return true;
    }
}
