package ru.ivanov.backend.controllers.controllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolation;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.ivanov.backend.dto.NotFoundError;
import ru.ivanov.backend.dto.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Hidden
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(value = ConstraintViolationException.class, produces = "application/json")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ValidationError> onConstraintValidationException(ConstraintViolationException e) {
        List<ValidationError> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.add(new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return errors;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class, produces = "application/json")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ValidationError> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationError> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return errors;
    }

    @ExceptionHandler(value = IllegalArgumentException.class, produces = "application/json")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public NotFoundError onMethodArgumentNotValidException(IllegalArgumentException e) {
        return new NotFoundError(e.getMessage());
    }
}
