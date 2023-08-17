package ru.kviak.springlibrary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "owner")
    private List<Book> items;
}