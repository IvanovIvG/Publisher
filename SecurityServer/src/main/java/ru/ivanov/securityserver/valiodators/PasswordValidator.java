package ru.ivanov.securityserver.valiodators;

import org.springframework.stereotype.Component;

/**
 * @author Ivan Ivanov
 **/
@Component
public class PasswordValidator {
    public boolean validate(String password){
        return true;
    }
}
