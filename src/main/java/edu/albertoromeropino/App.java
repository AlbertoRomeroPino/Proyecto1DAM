package edu.albertoromeropino;

import edu.albertoromeropino.viewController.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static Login login;

    @Override
    public void start(Stage stage) throws IOException {


        /*
        * codigo por defecto posi acaso
        scene = new Scene(loadFXML("viewController/UserScreen"), 640, 480);
        stage.setScene(scene);
        stage.show();*/
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}