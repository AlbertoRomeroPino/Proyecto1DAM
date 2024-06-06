package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    @Test
    void store() {

        Person person4 = new Person("PersonaPrueba", "31023450G", "@923abcd");

        PersonDAO.build().store(person4);

    }

    @Test
    void storeUpdate() {
        Person person1 = new Person("Pedro", "31264578I", "@121abd5");
        assertEquals(person1, PersonDAO.build().store(person1));
    }

    @Test
    void findID() {
        Person person = new Person("Alberto2", "31022430H", "@123abcd");
        System.out.println(PersonDAO.build().findID("Alberto2"));
    }

    @Test
    void deleteEntity() {
        Person person = new Person("Alberto4", "31023450G", "@923abcd");
        assertEquals(person, PersonDAO.build().deleteEntity(person));
    }
}