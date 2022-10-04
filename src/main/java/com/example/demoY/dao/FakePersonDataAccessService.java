package com.example.demoY.dao;

//import com.example.demo.model.Person;
import com.example.demoY.model.Person;
import org.springframework.stereotype.Repository;

//import java.util.UUID;

import java.util.*;

//import static java.lang.Character.getName;
import java.util.UUID;

//import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Repository("fakeDao")
public class FakePersonDataAccessService implements com.example.demoY.dao.PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public int insertPerson(Person person) {
        return com.example.demoY.dao.PersonDao.super.insertPerson(person);
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if(indexOfPersonToUpdate >= 0){
                        DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }


}
