package ru.ivanov.Publisher.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Ivan Ivanov
 */
@Entity
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 1, max = 100, message = "Name must be from 1 to 100 length")
    private String name;

    @Column(name = "value")
    private boolean value;

    public Test() {

    }

    public Test(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}