package ru.kviak.springlibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kviak.springlibrary.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
