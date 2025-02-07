package ru.ivanov.Publisher.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author Ivan Ivanov
 **/
@OpenAPIDefinition(
        info = @Info(
                title = "Publisher",
                description = "API системы для издательства",
                version = "1.0.0",
                contact = @Contact(
                        name = "Ivanov Ivan",
                        email = "pathfinder1123@mail.ru"
                )
        )
)
public class OpenApiConfig {
}
