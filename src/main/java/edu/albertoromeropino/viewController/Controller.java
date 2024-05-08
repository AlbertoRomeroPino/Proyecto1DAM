package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller {
    App app;
    public void updateApp (App app){
        this.app=app;
    }

    public abstract void openTab(Object input) throws IOException;
    public abstract void addNew()throws IOException;
    public abstract void initialize(URL localition, ResourceBundle resourceBundle);
}
