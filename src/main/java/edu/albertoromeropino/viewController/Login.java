package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.model.dao.PersonDAO;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.viewController.enums.Scenes;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login extends Controller implements Initializable {
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    private Controller controller;

    public void initialize(URL localition, ResourceBundle resourceBundle) {

    }

    @Override
    public void onOpen(Object imput) throws IOException {
        changeScene(Scenes.MENUBAR, null);
    }

    public void changeScene(Scenes scenes, Object data) throws IOException {
        View view = MenuBar.loadFXML(scenes);
        controller= view.controller;
        controller.onOpen(data);
    }

    @Override
    public void onClose(Object output) {

    }

    @FXML
    public void enterApp(Event event) throws IOException {
        //Person person = new Person(user.getText(), null, password.getText());
        //Las tres lineas de abajo son temporales
        Person person = Person.getPerson();
        person.setNickName(user.getText());
        person.setPassword(password.getText());


        if (person.getNickName() != null) {
            Person personDB = PersonDAO.build().findID(person.getNickName());
            System.out.println(person);
            if (person.getNickName().equals(personDB.getNickName()) /*&&
            person.getPassword().equals(personDB.getPassword())*/) {
                person = personDB;
                System.out.println(person);
                App.currentController.changeScene(Scenes.MENUBAR,null);
            }
        }
    }


    public void register(Event event) throws IOException {
        App.currentController.onOpen(Scenes.REGISTER);
    }
}