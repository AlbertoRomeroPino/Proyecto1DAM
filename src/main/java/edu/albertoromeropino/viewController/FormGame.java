package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.w3c.dom.events.Event;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormGame extends Controller implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private TextField person;
    @FXML
    private TextField company;

    private Controller controller;

    @Override
    public void openTab(Object imput) throws IOException {
        this.controller = (Controller) imput;
    }

    @Override
    public void onClose(Object output) {

    }

    @FXML
    private void saveAndClose(Event event){
        Game game = new Game();
        game.setName(name.getText());
        game.setCategory(category.getText());
        game.setPerson(Person.getPerson());
        //falta logros y compañia
    }
@FXML
    private void CreateArchievement(Event event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
