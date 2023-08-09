package ru.kviak.springlibrary.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class Book {

    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=1, max=100, message = "Name length should be between 1 and 100 characters")
    private String title;
    @NotEmpty(message = "Author should not be empty")
    @Size(min=1, max=100, message = "Author length should be between 1 and 100 characters")
    private String author;

    //private int person_id;
    @NotEmpty
    @Min(value = 1500, message = "The book must be published later than 1500")
    @Max(value = 2023, message = "Took could not be published later 2023")
    private int yearOfPublic;

    public Book(){}

    public Book(int id, String title, String author, int yearOfPublic) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublic = yearOfPublic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublic() {
        return yearOfPublic;
    }

    public void setYearOfPublic(int yearOfPublic) {
        this.yearOfPublic = yearOfPublic;
    }
}
