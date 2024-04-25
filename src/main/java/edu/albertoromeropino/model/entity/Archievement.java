package edu.albertoromeropino.model.entity;

public class Archievement {
    private int idArchievement;
    private String archievementName;
    private String descriptionArchievement;
    private String helpArchievement;
    private Game game;


    public Archievement(String archievementName,
                        String descriptionArchievement, String helpArchievement, Game game) {
        setArchievementName(archievementName);
        setDescriptionArchievement(descriptionArchievement);
        setHelpArchievement(helpArchievement);
        this.game = game;
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

    public int getIdArchievement() {
        return idArchievement;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setIdArchievement(int idArchievement) {
        this.idArchievement = idArchievement;
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
                ", archievementName='" + archievementName + '\'' +
                ", descriptionArchievement='" + descriptionArchievement + '\'' +
                ", helpArchievement='" + helpArchievement + '\'' +
                ", game=" + game +
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
