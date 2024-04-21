package edu.albertoromeropino.entity;

import edu.albertoromeropino.Utils.Passwords;

import java.util.Objects;

public class Person {
    private String nickName;
    private String dni;
    private String password;
    private Obtain obtain;

    private final int MIN_LENGTH = 8;
    private final int MAX_LENGTH = 12;

    public Person(String nickName, String dni, String password, Obtain obtain) {
        this.nickName = nickName;
        this.dni = dni;
        setPassword(password);
        this.obtain = obtain;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean setPassword(String newPassword) {
        Boolean passwordSet = false;
        if (newPassword.length() >= MIN_LENGTH && newPassword.length() <= MAX_LENGTH){
            this.password = Passwords.encryptPassword(newPassword);
            passwordSet = true;
        }
        return passwordSet;
    }

    public Obtain getObtain() {
        return obtain;
    }

    public void setObtain(Obtain obtain) {
        this.obtain = obtain;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nickName='" + nickName + '\'' +
                ", dni='" + dni + '\'' +
                ", obtain=" + obtain +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        boolean result ;
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
