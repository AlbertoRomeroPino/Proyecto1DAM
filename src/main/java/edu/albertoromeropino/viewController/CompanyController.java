package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.model.dao.CompanyDAO;
import edu.albertoromeropino.model.dao.GameDAO;
import edu.albertoromeropino.model.entity.Company;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.viewController.enums.Scenes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CompanyController extends Controller implements Initializable {
    @FXML
    private TableView<Company> tableView;
    @FXML
    private TableColumn<Company, String> columnNameCompany;
    @FXML
    private TableColumn<Company, String> columnDirector;
    @FXML
    private TableColumn<Company, String> columnCreation;

    private ObservableList<Company> company;

    @Override
    public void onOpen(Object input) throws IOException {
        ArrayList<Company> company = CompanyDAO.build().findAll();
        this.company = FXCollections.observableArrayList(company);
        tableView.setItems(this.company);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        columnNameCompany.setCellValueFactory(company -> new SimpleStringProperty(company.getValue().getNameCompany()));
        columnDirector.setCellValueFactory(company -> new SimpleStringProperty(company.getValue().getCompanyDirector()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        columnCreation.setCellValueFactory(company -> new SimpleStringProperty(company.getValue().getCompanyCreation().format(formatter)));
    }

    @FXML
    public void addCompany(Event event) throws IOException {
        App.currentController.openModal(Scenes.ADDCOMPANY, "Agregar un juego", this, null);
    }

    @FXML
    public void deleteCompany(Event event) throws IOException {
        CompanyDAO.build().deleteEntity(tableView.getSelectionModel().getSelectedItem());
        this.company.remove(tableView.getSelectionModel().getSelectedItem());
    }

    public void storeGame(Company newCompany) {
        CompanyDAO.build().store(newCompany);
        this.company.add(newCompany);
    }
}
