package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.ivanov.Publisher.models.validationGroups.OnCreate;

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
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    @NotBlank(message = "Name must not be empty", groups = OnCreate.class)
    private String name;
}
