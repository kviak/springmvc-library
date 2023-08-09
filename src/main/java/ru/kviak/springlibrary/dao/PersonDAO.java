package ru.kviak.springlibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kviak.springlibrary.models.Person;

import java.util.List;

@SuppressWarnings("deprecation")
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null); // Хотя бы один объект или NULL.
    }

    public void update(int id,Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET fullName=?, birthyear=? WHERE id=?",
                updatePerson.getFullName(), updatePerson.getBirthYear(), id);
    }

    public void create(Person person){
        jdbcTemplate.update("INSERT INTO Person(fullName, birthyear) VALUES (?,?)",
                person.getFullName(), person.getBirthYear());
    }
}
