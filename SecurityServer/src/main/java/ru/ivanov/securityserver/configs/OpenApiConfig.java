package ru.ivanov.securityserver.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author Ivan Ivanov
 **/
@OpenAPIDefinition(
        info = @Info(
                title = "Security server",
                description = "OAuth2 сервер авторизации, работает с пользователями",
                version = "0.0.1",
                contact = @Contact(
                        name = "Ivanov Ivan",
                        email = "pathfinder1123@mail.ru"
                )
        ),
        servers = {
                @Server(url = "http://localhost:9090/", description = "URL сервера")
        }
)
public class OpenApiConfig {
}
