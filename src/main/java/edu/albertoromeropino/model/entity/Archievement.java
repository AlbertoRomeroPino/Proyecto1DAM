package edu.albertoromeropino.model.entity;

public class Archievement {
    private int idArchievement;
    private String nameGame;
    private String archievementName;
    private String descriptionArchievement;
    private String helpArchievement;


    public Archievement(String nameGame, String archievementName,
                        String descriptionArchievement, String helpArchievement) {
        setNameGame(nameGame);
        setArchievementName(archievementName);
        setDescriptionArchievement(descriptionArchievement);
        setHelpArchievement(helpArchievement);
    }

    public String getHelpArchievement() {
        return helpArchievement;
    }

    public String getDescriptionArchievement() {
        return descriptionArchievement;
    }

    public String getArchievementName() {
        return archievementName;
    }

    public String getNameGame() {
        return nameGame;
    }

    public boolean setNameGame(String nameGame) {
        boolean nameGameSet = false;
        if (nameGame.length() < 50) {
            this.nameGame = nameGame;
        }
        return nameGameSet;
    }

    public boolean setArchievementName(String archievementName) {
        boolean archievementNameSet = false;
        if (archievementName.length() < 40) {
            this.archievementName = archievementName;
        }
        return archievementNameSet;
    }

    public boolean setDescriptionArchievement(String descriptionArchievement) {
        boolean descriptionSet = false;

        if (descriptionArchievement.length() < 80) {
            this.descriptionArchievement = descriptionArchievement;
        }
        return descriptionSet;
    }

    public boolean setHelpArchievement(String helpArchievement) {
        boolean helpSet = false;
        if (helpArchievement.length() < 200) {
            this.helpArchievement = helpArchievement;
        }
        return helpSet;
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
