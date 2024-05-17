package edu.albertoromeropino.model.dao;

import edu.albertoromeropino.model.entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    @Test
    void store() {
        Person person1 = new Person("Alberto1","31022430F","@123abcd");
        Person person2 = new Person("Alberto2","31022430H","@122abcd");
        Person person3 = new Person("Alberto3","32022430G","@121abcd");
        Person person = new Person("Alberto4","31023450G","@923abcd");

        PersonDAO.build().store(person);
        PersonDAO.build().store(person1);
        PersonDAO.build().store(person2);
        PersonDAO.build().store(person3);

        assertEquals (person, PersonDAO.build().findID(person.getNickName()));
        assertEquals (person1, PersonDAO.build().findID(person1.getNickName()));
        assertEquals (person2, PersonDAO.build().findID(person2.getNickName()));
        assertEquals (person3, PersonDAO.build().findID(person3.getNickName()));
    }

    @Test
    void storeUpdate(){
        Person person1 = new Person("Pedro", "31264578I", "@121abd5");
        assertEquals(person1, PersonDAO.build().store(person1));
    }

    @Test
    void findID() {
        Person person = new Person("Alberto2","31022430H","@123abcd");
        assertEquals(person, PersonDAO.build().findID("Alberto2"));
        System.out.println(PersonDAO.build().findID("Alberto2"));
    }

    @Test
    void deleteEntity() {
        Person person = new Person("Alberto4","31023450G","@923abcd");
        assertEquals(person, PersonDAO.build().deleteEntity(person));
    }
}