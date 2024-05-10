package edu.albertoromeropino.viewController.enums;

public enum Tab {
    LOGIN("viewController/Login"),
    REGISTER("viewController/Register"),
    GAME("viewController/Game"),
    COMPANY("viewController/Company"),
    ARCHIEVEMENT("viewController/Achievement"),
    MENUBAR("viewController/MenuBar");


    private String url;

    Tab(String url) {
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
    }
