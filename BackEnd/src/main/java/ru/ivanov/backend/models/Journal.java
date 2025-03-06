package ru.ivanov.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Entity
@Table(name = "journals")
@Data
@NoArgsConstructor
public class Journal {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;
}
