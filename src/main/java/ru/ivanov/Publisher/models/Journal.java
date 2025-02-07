package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
}
