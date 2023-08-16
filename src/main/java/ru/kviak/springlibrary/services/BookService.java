package ru.kviak.springlibrary.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.springlibrary.models.Book;
import ru.kviak.springlibrary.models.Person;
import ru.kviak.springlibrary.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService{

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findAll(int page, int perBooks, boolean sort){
        if (sort) return bookRepository.findAll(PageRequest.of(page,perBooks, Sort.by("publishingYear"))).getContent();
            else return bookRepository.findAll(PageRequest.of(page, perBooks)).getContent();
    }

    @Transactional(readOnly = true)
    public Optional<Book> findById(int id){
        return bookRepository.findById(id);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void free(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        foundBook.orElse(null).setOwner(null);
    }

    @Transactional
    public void set(int bookId, Person newOwner){
        Optional<Book> foundBook = bookRepository.findById(bookId);
        foundBook.orElse(null).setOwner(newOwner);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public Optional<Person>hasOwner(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        return Optional.ofNullable(foundBook.orElse(null).getOwner());
    }

    @Transactional
    public Optional<Book>search(String title){
        if (title.isEmpty()) // Если поисковая строка пришла пустой, возращаем пустую книгу
            return Optional.of(new Book());
        List<Book> list = bookRepository.findByTitleStartingWith(title);
        return list.isEmpty() ? Optional.of(new Book()) : Optional.of(list.get(0));
    }
}
