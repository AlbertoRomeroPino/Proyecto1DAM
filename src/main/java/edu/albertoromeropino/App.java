package edu.albertoromeropino;

import edu.albertoromeropino.viewController.MenuBar;
import edu.albertoromeropino.viewController.View;
import edu.albertoromeropino.viewController.enums.Scenes;
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
    public static MenuBar currentController;

    @Override
    public void start(Stage stage) throws IOException {
        //view/layout.fxml
        View view = MenuBar.loadFXML(Scenes.LOGIN);
        scene = new Scene(view.scene, 640, 480);
        currentController = (MenuBar) view.controller;
        currentController.onOpen(null);
        stage.setScene(scene);
        stage.show();
    }


    static void setRoot(String fxml) throws IOException {
        //scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}