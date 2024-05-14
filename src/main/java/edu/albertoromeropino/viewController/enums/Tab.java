package edu.albertoromeropino.viewController.enums;

public enum Tab {
    LOGIN("viewController/Login.fxml"),
    REGISTER("viewController/Register.fxml"),
    GAME("viewController/Game.fxml"),
    MENUBAR("viewController/MenuBar.fxml");


    private String url;

    Tab(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
