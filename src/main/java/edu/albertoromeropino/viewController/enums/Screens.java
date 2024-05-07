package edu.albertoromeropino.viewController.enums;

public enum Screens {
    LOGIN("viewController/Login"),
    REGISTER("viewController/Register"),
    GAME("viewController/Game"),
    COMPANY("viewController/Company"),
    ARCHIEVEMENT("viewController/Achievement");


    private String url;

    Screens(String url) {
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
    }
