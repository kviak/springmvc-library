package ru.kviak.springlibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.springlibrary.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
