package edu.albertoromeropino.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Obtain {
    private String nickName;
    private Archievement id_archievement;
    private LocalDate dateObtained;


    public Obtain(String nickName, Archievement id_archievement, LocalDate dateObtained) {
        this.nickName = nickName;
        this.id_archievement = id_archievement;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Archievement getId_archievement() {
        return id_archievement;
    }

    public void setId_archievement(Archievement id_archievement) {
        this.id_archievement = id_archievement;
    }
}
