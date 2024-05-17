package edu.albertoromeropino;

import edu.albertoromeropino.model.dao.PersonDAO;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.viewController.MenuBar;
import edu.albertoromeropino.viewController.View;
import edu.albertoromeropino.viewController.enums.Tab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene; // donde se ubica esta
    public static Stage stage; // pestaña de navegador como de aplicacion
    public static MenuBar appController;

    @Override
    public void start(Stage stage) throws IOException {
        View view = MenuBar.loadFXML(Tab.LOGIN);
        scene = new Scene(view.tab, 640,480);
        appController = (MenuBar) view.controller;
        appController.openTab(null);
        stage.setScene(scene);
        stage.show();

        /*//codigo por defecto posi acaso
        scene = new Scene(loadFXML("viewController/Login"), 640, 480);
        stage.setScene(scene);
        stage.show();*/
    }

    static void setRoot(String fxml) throws IOException {
        //scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}