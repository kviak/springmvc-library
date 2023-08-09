package ru.kviak.springlibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kviak.springlibrary.models.Book;
import ru.kviak.springlibrary.models.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> showPersonBook(int id){

        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id =" + id,
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElse(null); // Хотя бы один объект или NULL.
    }

    public void create(Book book){
        jdbcTemplate.update("INSERT INTO Book(title, author, yearofpublic) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublic());
    }

    public void set(String id){
        int[] numbers = Arrays.stream(id.split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(Arrays.toString(numbers));
        int person_id = numbers[0];
        int book_id = numbers[1];
        System.out.println("Person: " + person_id); // GAVNA
        System.out.println("Book: " + book_id);

        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?",
                person_id, book_id);
    }

    public Optional<Person>isHadOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id=Person.id" +
                " WHERE Book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void free(int id){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", null, id);
    }


}
