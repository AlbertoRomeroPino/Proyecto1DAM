package edu.albertoromeropino.viewController;


import edu.albertoromeropino.model.dao.GameDAO;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;


public class GameController extends Controller implements Initializable {
    @FXML
    private TableView<Game> tableView;
    @FXML
    private TableColumn<Game, Integer> columnIdGame;
    @FXML
    private TableColumn<Game, String> columnName;
    @FXML
    private TableColumn<Game, String> columnCategory;
    @FXML
    private TableColumn<Game, String> columnPerson;
    @FXML
    private TableColumn<Game, String> columnCompany;

    private ObservableList<Game> games;

    @Override
    public void openTab(Object entity) throws IOException {


        List<Game> games = (List<Game>) GameDAO.build().findByPerson(Person.getPerson().getNickName());
        System.out.println(games);
        this.games = (ObservableList<Game>) FXCollections.observableList(games);
        tableView.setItems(this.games);

    }

    @Override
    public void onClose(Object output) {

    }

    private void addGame()throws IOException{

    }


    public void initialize(URL localition, ResourceBundle resourceBundle) {
        columnIdGame.setCellValueFactory(game -> new SimpleIntegerProperty(game.getValue().getIdGame()).asObject());
        columnIdGame.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }
}
