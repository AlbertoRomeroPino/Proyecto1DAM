package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.entity.Person;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import org.w3c.dom.events.Event;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Register {

    @FXML
    private TextField user;
    @FXML
    private TextField dni;
    @FXML
    private PasswordField password;

    public void initializa (URL localition, ResourceBundle resourceBundle){

    }

    @FXML
    private void registerApp (Event event) throws IOException{
        Person person = new Person();
        person.setNickName(user.getText());
        person.setDni(dni.getText());
        person.setPassword(password.getText());

    }

}
