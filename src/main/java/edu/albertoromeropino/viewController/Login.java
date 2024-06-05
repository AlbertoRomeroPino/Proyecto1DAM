package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.model.dao.PersonDAO;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.viewController.enums.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    }

    @Override
    public void onClose(Object output) {

    }

    @FXML
    private void enterApp(Scenes event) throws IOException {
        Person person = new Person();
        person.setNickName(user.getText());
        person.getPassword().equals(person.setPassword(password.getText()));
        Person personDB = PersonDAO.build().findID(person.getNickName());

        if (person.getPassword().equals(personDB.getPassword())) {
            person = personDB;

        }
    }


}
