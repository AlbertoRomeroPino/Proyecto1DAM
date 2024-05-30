package edu.albertoromeropino.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
    private String nameCompany;
    private String companyDirector;
    private LocalDate companyCreation;
    private List<Game> games;

    public Company(String nameCompany, String companyDirector, LocalDate companyCreation) {
        setNameCompany(nameCompany);
        setCompanyDirector(companyDirector);
        setCompanyCreation(companyCreation);
        //setGames(games);
    }

    public Company() {
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getCompanyDirector() {
        return companyDirector;
    }

    public LocalDate getCompanyCreation() {
        return companyCreation;
    }

    public List<Game> getGames() {
        return games;
    }

    public boolean setNameCompany(String nameCompany) {
        boolean nameSet = false;
        if (nameCompany.length() < 40){
            this.nameCompany = nameCompany;
            nameSet = true;
        }
        return nameSet;
    }

    public boolean setCompanyDirector(String companyDirector) {
        boolean directorSet = false;
        if (companyDirector.length() < 40){
            this.companyDirector = companyDirector;
            directorSet = true;
        }
        return directorSet;
    }

    public void setCompanyCreation(LocalDate companyCreation) {
        this.companyCreation = companyCreation;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGames(Game game) {
        if (game != null){
            games.add(game);
        }
    }

    public void removeGames(Game game) {
        if (game != null) {
            games.remove(game);
        }
    }

    public void updateGames(Game gameOld, Game gameNew){
        if (gameNew != null && gameOld != null){
            games.remove(gameOld);
            games.add(gameNew);
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "nameCompany='" + nameCompany + '\'' +
                ", companyDirector='" + companyDirector + '\'' +
                ", companyCreation=" + companyCreation +
                ", games=" + games +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) result = true;
        if (o == null || getClass() != o.getClass()) result = false;
        Company company = (Company) o;
        result = Objects.equals(nameCompany, company.nameCompany);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameCompany);
    }
}
