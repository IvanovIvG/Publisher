package ru.ivanov.backend.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Configuration
public class SecurityConfig {
    @Value("${keySetURI}")
    private String keySetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                c -> c.jwt(
                        j -> j.jwkSetUri(keySetUri)
                )
        );

        http.csrf(
                AbstractHttpConfigurer::disable
        );

        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors(c -> {
//            CorsConfigurationSource source = request -> {
//                CorsConfiguration config = new CorsConfiguration();
//                config.setAllowedOrigins(
//                        List.of("http://localhost:3000"));
//                config.setAllowedMethods(
//                        List.of("*"));
//                config.setAllowedHeaders(List.of("*"));
//                return config;
//            };
//            c.configurationSource(source);
//        });
//        http.csrf(
//                AbstractHttpConfigurer::disable
//        );
//        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
//        return http.build();
//    }
}
