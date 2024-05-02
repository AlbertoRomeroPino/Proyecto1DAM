package edu.albertoromeropino.viewController.enums;

public enum Screens {
    DELETE_USER("viewController/DeleteUser"),
    INFOUSER("viewController/InfoUser"),
    LOGIN("viewController/Login"),
    REGISTER("viewController/Register"),
    UPDATE_USER("viewController/UpdateUser"),
    USER_SCREEN("viewController/UserScreen");

    private String url;

    Screens(String url) {
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
    }
