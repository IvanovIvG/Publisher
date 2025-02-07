package ru.ivanov.Publisher.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author Ivan Ivanov
 **/
@OpenAPIDefinition(
        info = @Info(
                title = "Publisher",
                description = "API системы для издательства",
                version = "0.0.1",
                contact = @Contact(
                        name = "Ivanov Ivan",
                        email = "pathfinder1123@mail.ru"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080/", description = "URL сервера по умолчанию")
        }
)
public class OpenApiConfig {
}
