package edu.albertoromeropino.model.entity;

import java.util.Objects;
import java.util.Set;

public class Game {
    private int idGame;
    private String name;
    private String pegi;
    private Set<String> Category;
    private Person person;
    private Set<Archievement> archievements;

    public Game(int idGame, String name, String pegi, Set<String> Category, Person person, Set<Archievement> archievements) {
        this.idGame = idGame;
        this.name = name;
        this.pegi = pegi;
        this.Category = Category;
        this.person = person;
        this.archievements = archievements;
    }

    public int getIdGame() {
        return idGame;
    }

    public String getName() {
        return name;
    }


    public String getPegi() {
        return pegi;
    }

    public Set<String> getCategory() {
        return Category;
    }

    public Person getPerson() {
        return person;
    }

    public Set<Archievement> getArchievements() {
        return archievements;
    }

    public boolean setIdGame(int idGame) {
        boolean idSet = false;
        if (idGame < 99999 && idGame > 0) {
            this.idGame = idGame;
        }
        return idSet;
    }

    public boolean setName(String name) {
        boolean nameSet= false;
        if (name.length() <= 50) {
            this.name = name;
        }
        return nameSet;
    }

    public void setPegi(String pegi) {
        this.pegi = pegi;
    }

    public void setCategory(Set<String> category) {
        this.Category = category;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setArchievements(Set<Archievement> archievements) {
        this.archievements = archievements;
    }

    @Override
    public String toString() {
        return "Game{" +
                "idGame=" + idGame +
                ", name='" + name + '\'' +
                ", pegi='" + pegi + '\'' +
                ", gameType=" + Category +
                ", person=" + person +
                ", archievements=" + archievements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return idGame == game.idGame;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idGame);
    }
}
