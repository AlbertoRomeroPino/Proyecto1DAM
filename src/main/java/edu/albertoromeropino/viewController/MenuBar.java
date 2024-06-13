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



    @FXML
    private void goToGame() throws IOException {
        changeScene(Scenes.GAME, null);
    }

    @FXML
    private void goToCompany() throws IOException {
        changeScene(Scenes.COMPANY, null);
    }
}
