package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;

import java.io.IOException;


public abstract class Controller {
    App app;
    public void setApp(App app){
        this.app=app;
    }

    public abstract void onOpen(Object imput) throws IOException;
    public abstract void onClose(Object output);
}
