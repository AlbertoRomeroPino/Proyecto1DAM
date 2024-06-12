package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.dao.CompanyDAO;
import edu.albertoromeropino.model.entity.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddGame extends Controller implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private ComboBox<Company> company;

    private ObservableList<Company> companies;

    @Override
    public void onOpen(Object imput) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Company> companies = CompanyDAO.build().findAll();
        this.companies = FXCollections.observableArrayList(companies);
        company.setItems(this.companies);
    }


    @FXML
    private void closeWindow(Event event) {

        //Game game = new Game(id.getText(), name.getText(), category.getText(), Person.getPerson(), CompanyDAO.build().findID());
    }
}
