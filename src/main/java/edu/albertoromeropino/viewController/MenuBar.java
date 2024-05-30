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
    public void openTab(Object imput) throws IOException {
        changeTab(Scenes.GAME, null);
    }

    @Override
    public void onClose(Object output) {

    }

    public void changeTab(Scenes scenes, Object data) throws IOException {
        View view = loadFXML(scenes);
        borderPane.setCenter(view.scenes);
        this.centerController = view.controller;
        this.centerController.openTab(data);

    }

    public void openModal (Scenes scenes, String title, Controller parent, Object data) throws IOException{
        View view = loadFXML(scenes);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scenes);
        stage.setScene(_scene);
        view.controller.openTab(parent);
        stage.showAndWait();

    }

// Los 3 de abajo son para la menuBar Help
    @FXML
    private void Exit(){
        System.exit(0);
    }
    @FXML
    private void Error(){
        Exit();
    }
    @FXML
    private void Help(){
        Exit();
    }



    public void initialize(URL localition, ResourceBundle resourceBundle) {

    }

    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getUrl();
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent parent = loader.load();
        Controller controller = loader.getController();
        View view = new View();
        view.scenes = parent;
        view.controller = controller;
        return view;
    }

    private void goToGame() throws IOException{
        System.out.println(Scenes.GAME);
        changeTab(Scenes.GAME, null);
    }
}
