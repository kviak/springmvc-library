package ru.kviak.springlibrary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@ToString
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=1, max=100, message = "Name length should be between 1 and 100 characters")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min=1, max=100, message = "Author length should be between 1 and 100 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "The book must be published later than 1500")
    @Column(name = "year")
    private int publishingYear;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

}
