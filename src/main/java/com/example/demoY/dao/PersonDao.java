package com.example.demoY.dao;

//import com.example.demo.model.Person;
import com.example.demoY.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PersonDao {
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {//uuid by default
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    List<Person> selectAllPeople();
    Optional<Person> selectPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person person);

}