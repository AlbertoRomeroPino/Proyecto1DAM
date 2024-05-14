package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.viewController.enums.Tab;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import org.apache.commons.logging.Log;
import org.w3c.dom.events.Event;

import java.awt.*;
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
    public void openTab(Object imput) throws IOException {
            enterApp(Tab.LOGIN);
    }

    @Override
    public void onClose(Object output) {

    }

    @FXML
    private void enterApp (Tab event) throws IOException{
        Person person = new Person();
        person.setNickName(user.getText());
        person.setPassword(password.getText());

    }

    public static View loadFXML(Tab tab) throws IOException {
        String url = tab.getUrl();
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent parent = loader.load();
        Controller controller = loader.getController();
        View view = new View();
        view.tab = parent;
        view.controller = controller;
        return view;
    }



}
