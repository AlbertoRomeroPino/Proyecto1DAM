package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.entity.Company;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCompany extends Controller implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField director;
    @FXML
    private DatePicker creation;
    private CompanyController controller;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void addGame(Event event) {

            Company company = new Company(name.getText(), director.getText(), creation.getValue());
            this.controller.storeGame(company);
            ((Node) (event.getSource())).getScene().getWindow().hide();

    }
}
