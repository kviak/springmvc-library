package ru.kviak.springlibrary.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.springlibrary.models.Book;
import ru.kviak.springlibrary.models.Person;
import ru.kviak.springlibrary.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService{

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findById(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
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
    public Optional<Person>isHadOwner(int id){

        Optional<Book> foundBook = bookRepository.findById(id);
        return Optional.ofNullable(foundBook.orElse(null).getOwner());
    }


}
