package ru.ivanov.Publisher.controllers.controllerAdvice;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Getter
@Setter
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();
}