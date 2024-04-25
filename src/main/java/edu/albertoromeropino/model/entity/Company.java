package edu.albertoromeropino.model.entity;

import java.util.Date;
import java.util.Set;

public class Company {
    private String nameCompany;
    private String companyDirector;
    private Date companyCreation;
    private Set<Game> games;

    public Company(String nameCompany, String companyDirector, Date companyCreation, Set<Game> games) {
        setNameCompany(nameCompany);
        setCompanyDirector(companyDirector);
        this.companyCreation = companyCreation;
        this.games = games;
    }


    public String getNameCompany() {
        return nameCompany;
    }

    public String getCompanyDirector() {
        return companyDirector;
    }

    public Date getCompanyCreation() {
        return companyCreation;
    }

    public Set<Game> getGames() {
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

    public void setCompanyCreation(Date companyCreation) {
        this.companyCreation = companyCreation;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
