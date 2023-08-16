package ru.kviak.springlibrary.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.springlibrary.models.Book;
import ru.kviak.springlibrary.models.Person;
import ru.kviak.springlibrary.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;


    @Transactional(readOnly = true)
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public List<Book> showPersonBook(int id){
        Optional<Person> opt = peopleRepository.findById(id);
        return opt.map(Person::getItems).orElse(null);
    }
}
