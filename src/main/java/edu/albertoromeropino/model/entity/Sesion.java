package edu.albertoromeropino.model.entity;

public class Sesion {
    private Person person;
    private static Sesion _sessionStarted;

    public static Sesion getSesion() {
        if (_sessionStarted == null) {
            _sessionStarted = new Sesion();
        }
        return _sessionStarted;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
