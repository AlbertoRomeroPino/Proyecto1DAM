package edu.albertoromeropino.model.entity;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getNickName() {
        Person person = new Person();
        person.setNickName("Alberto");
        assertTrue(person.getNickName() == "Alberto");
    }

    @Test
    void getDni() {
        Person person = new Person();
        person.setDni("31022430F");
        assertTrue(person.getDni() == "31022430F");
    }

    @Test
    void getPassword() {
        Person person = new Person();
        person.setPassword("Alberto123@");
        System.out.println(person.getPassword());
        assertTrue(person.getPassword().equals("d14654b17f9f9e3c0b3be1dfb3420e03e1e762e59368de1e7d8bef728924a7b5"));
    }

}