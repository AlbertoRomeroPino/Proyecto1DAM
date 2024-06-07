package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.model.dao.PersonDAO;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.viewController.enums.Scenes;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Register extends Controller implements Initializable {

    @FXML
    private TextField nickName;
    @FXML
    private TextField dni;
    @FXML
    private PasswordField password;

    @Override
    public void onOpen(Object imput) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void registerPerson(Event event) throws IOException {
        //Person person = new Person(user.getText(), dni.getText(), password.getText());
        Person person = new Person();
        person.setNickName(nickName.getText());
        person.setDni(dni.getText());
        person.setPassword(password.getText());

        if (!person.getNickName().equals("")) {
            Person personDB = PersonDAO.build().findID(person.getNickName());

            if (personDB.getNickName() == null || personDB.getNickName().equals("")) {
                PersonDAO.build().store(person);
                App.setRoot(Scenes.LOGIN);
            } else {
                //Mensaje de "Ya existe este usuario"

            }
        }
    }
}
