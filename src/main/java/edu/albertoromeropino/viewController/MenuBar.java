package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.viewController.enums.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBar extends Controller implements Initializable {

    @FXML
    private BorderPane borderPane;
    private Controller centerController;

    @Override
    public void onOpen(Object imput) throws IOException {
        changeScene(Scenes.GAME, null);
    }

    public void changeScene(Scenes scenes, Object data) throws IOException {
        View view = loadFXML(scenes);
        borderPane.setCenter(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data);

    }

    public void openModal(Scenes scene, String title, Controller parent, Object data) throws IOException {
        View view = loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        stage.setScene(_scene);
        view.controller.onOpen(parent);
        stage.showAndWait();

    }

    @Override
    public void onClose(Object output) {

    }


    // Los 3 de abajo son para la menuBar Help
    @FXML
    private void Exit() {
        System.exit(0);
    }

    @FXML
    private void Error() {
        Exit();
    }

    @FXML
    private void Help() {
        Exit();
    }


    public void initialize(URL localition, ResourceBundle resourceBundle) {

    }

    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent parent = loader.load();
        Controller controller = loader.getController();
        View view = new View();
        view.scene = parent;
        view.controller = controller;
        return view;
    }

    private void goToGame() throws IOException {
        System.out.println(Scenes.GAME);
        changeScene(Scenes.GAME, null);
    }
}
