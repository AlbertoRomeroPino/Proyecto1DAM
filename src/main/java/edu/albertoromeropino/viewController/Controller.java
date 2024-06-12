package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.viewController.enums.Scenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public abstract class Controller {
    App app;

    public void setApp(App app) {
        this.app = app;
    }


    public abstract void onOpen(Object imput) throws IOException;

    public abstract void onClose(Object output);

    public void openModal(Scenes scene, String title, Controller parent, Object data) throws IOException {
        View view = MenuBar.loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        stage.setScene(_scene);
        stage.showAndWait();
    }



}

