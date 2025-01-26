package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Entity
@Table(name = "journals")
public class Journal {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy="journal", cascade = CascadeType.REMOVE)
    private List<Article> articles = new ArrayList<>();

    @Column(name = "name")
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    private String name;


    public Journal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name must not be empty") @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name must not be empty") @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length") String name) {
        this.name = name;
    }
}
