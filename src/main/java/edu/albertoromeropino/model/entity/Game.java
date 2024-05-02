package edu.albertoromeropino.model.entity;

import java.util.Objects;
import java.util.Set;

public class Game {
    private int idGame;
    private String name;
    private String Category;
    private Person person;
    private Set<Archievement> archievements;
    private Company company;

    public Game(int idGame, String name, String Category, Person person,
                Set<Archievement> archievements, Company company) {
        setIdGame(idGame);
        setName(name);
        setCategory(Category);
        setPerson(person);
        setArchievements(archievements);
        setCompany(company);
    }

    public Game() {
    }

    public int getIdGame() {
        return idGame;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return Category;
    }

    public Person getPerson() {
        return person;
    }

    public Set<Archievement> getArchievements() {
        return archievements;
    }

    public Company getCompany() {
        return company;
    }

    public boolean setIdGame(int idGame) {
        boolean idSet = false;
        if (idGame < 99999 && idGame >= 0) {
            this.idGame = idGame;
            idSet = true;
        }
        return idSet;
    }

    public boolean setName(String name) {
        boolean nameSet = false;
        if (name.length() <= 50) {
            this.name = name;
            nameSet = true;
        }
        return nameSet;
    }

    public boolean setCategory(String category) {
        boolean categorySet = false;
        if (category.length() < 30) {
            this.Category = category;
            categorySet = true;
        }
        return categorySet;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setArchievements(Set<Archievement> archievements) {
        this.archievements = archievements;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void addArchievements(Archievement archievement) {
        if (archievement != null) {
            archievements.add(archievement);
        }
    }

    public void removeArchievements(Archievement archievement) {
        if (archievement != null) {
            archievements.remove(archievement);
        }
    }

    public void updateArchievements(Archievement archievementOld, Archievement archievementNew) {
        if (archievementNew != null && archievementOld != null) {
            archievements.remove(archievementOld);
            archievements.add(archievementNew);
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "idGame=" + idGame +
                ", name='" + name + '\'' +
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
