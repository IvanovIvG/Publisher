package ru.ivanov.Publisher.controllers.controllerAdvice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ivan Ivanov
 **/
@RequiredArgsConstructor
@Getter
public class ValidationError {

    private final String fieldName;
    private final String message;

}