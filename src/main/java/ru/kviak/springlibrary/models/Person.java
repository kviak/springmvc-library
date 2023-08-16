package ru.kviak.springlibrary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 1900, message = "Min born date 1900")
    @Column(name = "birthyear")
    private int birthYear;

    @Size(min=2, max=100, message = "Full name length should be between 2 and 100 characters")
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "fullname")
    private String fullName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner") // Изменить на lazy.
    private List<Book> items;

    public Person() {}
    public Person(int id, int birthYear, String fullName) {
        this.id = id;
        this.birthYear = birthYear;
        this.fullName = fullName;
    }

    public List<Book> getItems() {
        return items;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}