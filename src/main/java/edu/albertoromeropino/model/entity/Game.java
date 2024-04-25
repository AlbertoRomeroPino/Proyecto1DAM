package edu.albertoromeropino.model.entity;

import edu.albertoromeropino.model.enums.GameType;
import edu.albertoromeropino.model.enums.Pegi;

public class Game {
    private int idGame;
    private String name;
    private Pegi pegi;
    private GameType[] gameType;
    private Person person;

    public Game(int idGame, String name, Pegi pegi, GameType[] gameType, Person person) {
        this.idGame = idGame;
        this.name = name;
        this.pegi = pegi;
        this.gameType = gameType;
        this.person = person;
    }

    public int getIdGame() {
        return idGame;
    }

    public String getName() {
        return name;
    }

    public Pegi getPegi() {
        return pegi;
    }

    public GameType[] getGameType() {
        return gameType;
    }

    public Person getPerson() {
        return person;
    }

    public boolean setIdGame(int idGame) {
        boolean idSet = false;
        if (idGame < 99999 && idGame > 0) {
            this.idGame = idGame;
        }
        return idSet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPegi(Pegi pegi) {
        this.pegi = pegi;
    }

    public void setGameType(GameType[] gameType) {
        this.gameType = gameType;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
