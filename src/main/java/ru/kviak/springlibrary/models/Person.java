package ru.kviak.springlibrary.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @Min(value = 1900, message = "Min born date 1900")
    private int birthYear;
    @Size(min=2, max=100, message = "Full name length should be between 2 and 100 characters")
    @NotEmpty(message = "Name should not be empty")
    private String fullName;

    public Person() {}
    public Person(int id, int birthYear, String fullName) {
        this.id = id;
        this.birthYear = birthYear;
        this.fullName = fullName;
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