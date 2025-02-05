package ru.ivanov.Publisher.controllers.controllerAdvice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Ivan Ivanov
 **/
@RequiredArgsConstructor
@Getter
public class Violation {

    private final String fieldName;
    private final String message;

}