package ru.ivanov.securityserver.dto;

import jakarta.validation.Constraint;
import ru.ivanov.securityserver.validators.UsernameValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Ivan Ivanov
 **/
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface UniqueUsername {
    String message() default "Username must be unique";
}
