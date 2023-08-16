package ru.kviak.springlibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.springlibrary.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleStartingWith(String name);
}
