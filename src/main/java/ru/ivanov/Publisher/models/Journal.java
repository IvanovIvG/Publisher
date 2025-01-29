package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Entity
@Table(name = "journals")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Journal {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @OneToMany(mappedBy="journal", cascade = CascadeType.REMOVE)
    private List<Article> articles = new ArrayList<>();

    @Column(name = "name")
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    @Getter
    @Setter
    private String name;
}
