package edu.albertoromeropino.model.entity;


import edu.albertoromeropino.utils.Validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Person {
    private String nickName;
    private String dni;
    private String password;
    private List<Game> games;
    private static Person _sessionStarted;

    public Person(String nickName, String dni, String password, ArrayList<Game> games) {
        setNickName(nickName);
        setDni(dni);
        setPassword(password);
        setGames(games);
    }

    public Person() {
    }

    public Person(String nickName, String dni, String password) {
        setNickName(nickName);
        setDni(dni);
        setPassword(password);
    }

    public static Person getPerson() {
        if (_sessionStarted == null) {
            _sessionStarted = new Person();
        }
        return _sessionStarted;
    }

    public String getNickName() {
        return nickName;
    }

    public String getDni() {
        return dni;
    }

    public String getPassword() {
        return password;
    }

    public List<Game> getGames() {
        return games;
    }


    public boolean setNickName(String nickName) {
        boolean add = false;
        //if (Validations.validateNickName(nickName)) {
        this.nickName = nickName;
        add = true;
        //}
        return add;
    }

    public boolean setDni(String dni) {
        boolean dniSet = false;
        if (Validations.validateDni(dni)) {
            this.dni = dni;
            dniSet = true;
        }
        return dniSet;
    }

    public boolean setPassword(String newPassword) {
        Boolean passwordSet = false;

        this.password = newPassword;
        passwordSet = true;
        return passwordSet;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGames(Game game) {
        if (game != null) {
            this.games.add(game);
        }
    }

    public void removeGames(Game game) {
        if (game != null) {
            this.games.remove(game);
        }
    }

    public void updateGames(Game gameOld, Game gameNew) {
        if (gameNew != null && gameOld != null) {
            games.remove(gameOld);
            games.add(gameNew);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "nickName='" + nickName + '\'' +
                ", dni='" + dni + '\'' +
                ", password='" + password + '\'' +
                ", games=" + games +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) result = true;
        if (o == null || getClass() != o.getClass()) result = false;
        Person person = (Person) o;
        result = Objects.equals(nickName, person.nickName) && Objects.equals(password, person.password);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickName);
    }


}
