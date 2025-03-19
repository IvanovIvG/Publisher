package ru.ivanov.securityserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Ivan Ivanov
 **/
@Data
@AllArgsConstructor
public class Role implements GrantedAuthority {
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
