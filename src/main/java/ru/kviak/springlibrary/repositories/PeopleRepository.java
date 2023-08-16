package ru.kviak.springlibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kviak.springlibrary.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
