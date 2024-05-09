package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    @Test
    void store() {
        Person person1 = new Person("Alberto1","31022430F","@123abcd");
        assertEquals (person1, PersonDAO.build().store(person1));
        Person person2 = new Person("Alberto2","31022430H","@123abcd");
        assertEquals (person2, PersonDAO.build().store(person2));
        Person person3 = new Person("Alberto3","32022430G","@123abcd");
        assertEquals (person3, PersonDAO.build().store(person3));

    }

    @Test
    void findID() {
        Person person = new Person("Alberto2","31022430H","@123abcd");
        assertEquals(person, PersonDAO.build().findID("Alberto2"));
    }

    @Test
    void deleteEntity() {
        Person person = new Person("Alberto3","31022430G","@123abcd");
        assertEquals(person, PersonDAO.build().deleteEntity(person));
    }
}