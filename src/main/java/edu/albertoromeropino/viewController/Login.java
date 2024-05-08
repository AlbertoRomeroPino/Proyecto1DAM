package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.entity.Person;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import org.w3c.dom.events.Event;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login {
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;

    public void initialize(URL localition, ResourceBundle resourceBundle) {

    }

    @FXML
    private void enterApp (Event event) throws IOException{
        Person person = new Person();
        person.setNickName(user.getText());
        person.setPassword(password.getText());

    }
}
