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

    public void initialize(URL localition, ResourceBundle resourceBundle) {

    }

    @Override
    public void onOpen(Object input) throws IOException {
        showScene(Scenes.LOGIN, null);
    }

    public void showScene(Scenes scenes, Object data) throws  IOException{
        View view = MenuBar.loadFXML(scenes);
    }

    public void changeScene(Scenes scenes, Object data) throws IOException {
        App.setRoot(scenes, null);
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


        if (person.getNickName() != null || person.getNickName().equals("")) {
            Person personDB = PersonDAO.build().findID(person.getNickName());
            System.out.println(person);
            if (person.getNickName().equals(personDB.getNickName()) /*&&
            person.getPassword().equals(personDB.getPassword())*/) {
                person = personDB;
                System.out.println(person);
                changeScene(Scenes.MENUBAR, null);
            }else {
                //Manda pantalla de error. Futuras actualizaciones
            }
        }
    }

    @FXML
    public void registerInApp(Event event) throws IOException {
        changeScene(Scenes.REGISTER, null);
    }

}