package edu.albertoromeropino.entity;

import java.util.Objects;

public class Archievement {
    private int idArchievement;
    private String nameGame;
    private String archievementName;
    private String descriptionArchievement;
    private String helpArchievement;
    private Obtain obtain;

    public Archievement(String nameGame, String archievementName,
                        String descriptionArchievement, String helpArchievement) {
        this.nameGame = nameGame;
        this.archievementName = archievementName;
        this.descriptionArchievement = descriptionArchievement;
        this.helpArchievement = helpArchievement;
    }

    public String getHelpArchievement() {
        return helpArchievement;
    }

    public void setHelpArchievement(String helpArchievement) {
        this.helpArchievement = helpArchievement;
    }

    public String getDescriptionArchievement() {
        return descriptionArchievement;
    }

    public void setDescriptionArchievement(String descriptionArchievement) {
        this.descriptionArchievement = descriptionArchievement;
    }

    public String getArchievementName() {
        return archievementName;
    }

    public void setArchievementName(String archievementName) {
        this.archievementName = archievementName;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    @Override
    public String toString() {
        return "Archievement{" +
                "idArchievement=" + idArchievement +
                ", nameGame='" + nameGame + '\'' +
                ", archievementName='" + archievementName + '\'' +
                ", descriptionArchievement='" + descriptionArchievement + '\'' +
                ", helpArchievement='" + helpArchievement + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archievement that = (Archievement) o;
        return idArchievement == that.idArchievement;
    }

}
