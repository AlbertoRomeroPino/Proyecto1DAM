package edu.albertoromeropino.model.entity;

import edu.albertoromeropino.model.Utils.Validations;

import java.util.Objects;
import java.util.Set;


public class Person {
    private String nickName;
    private String dni;
    private String password;
    private Set<Archievement> archievements;

    private final int MIN_LENGTH = 8;
    private final int MAX_LENGTH = 12;

    public Person(String nickName, String dni, String password, Set<Archievement> archievements) {
        setNickName(nickName);
        setDni(dni);
        setPassword(password);
        this.archievements = archievements;
    }

    public String getNickName() {
        return nickName;
    }

    public String getDni() {
        return dni;
    }

    // No hay get de contraseña porque no se debe ver aunque este encriptada
    public Set<Archievement> getArchievements() {
        return archievements;
    }

    public void setArchievements(Set<Archievement> archievements) {
        this.archievements = archievements;
    }

    public boolean setNickName(String nickName) {
        boolean add = false;
        if (Validations.validateNickName(nickName)) {
            this.nickName = nickName;
            add = true;
        }
        return add;
    }

    public boolean setDni(String dni) {
        boolean dniSet = false;
        if (Validations.validateDni(dni)) {

            dniSet = true;
        }
        return dniSet;
    }

    public boolean setPassword(String newPassword) {
        Boolean passwordSet = false;
        if (newPassword.length() >= MIN_LENGTH && newPassword.length() <= MAX_LENGTH) {
            this.password = Validations.encryptPassword(newPassword);
            passwordSet = true;
        }
        return passwordSet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nickName='" + nickName + '\'' +
                ", dni='" + dni + '\'' +
                ", password='" + password + '\'' +
                ", archievements=" + archievements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) result = true;
        if (o == null || getClass() != o.getClass()) result = false;
        Person person = (Person) o;
        result = Objects.equals(nickName, person.nickName);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickName);
    }


}
