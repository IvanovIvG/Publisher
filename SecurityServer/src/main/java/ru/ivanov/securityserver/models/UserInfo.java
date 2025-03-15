package ru.ivanov.securityserver.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Entity
@Table(name = "user_info")
@Data
@NoArgsConstructor
public class UserInfo {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String username;
}
