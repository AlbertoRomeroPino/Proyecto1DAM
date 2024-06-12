package edu.albertoromeropino.viewController.enums;

public enum Scenes {
    LOGIN("viewController/Login.fxml"),
    REGISTER("viewController/Register.fxml"),

    GAME("viewController/Game.fxml"),
    ADDGAME("viewController/AddGame.fxml"),

    COMPANY("viewController/Company.fxml"),

    ARCHIEVEMENT("viewController/Archievement.fxml"),

    MENUBAR("viewController/MenuBar.fxml");


    private String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
