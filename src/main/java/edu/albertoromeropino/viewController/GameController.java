package edu.albertoromeropino.viewController;


import edu.albertoromeropino.model.dao.GameDAO;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class GameController extends Controller {
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
        List<Game> gamestmp = (List<Game>) GameDAO.build().findByPerson(Person.getPerson().getNickName());
        games = (ObservableList<Game>)  FXCollections.observableList(gamestmp);
        tableView.setItems((ObservableList<Game>) games);
    }

    @Override
    public void onClose(Object output) {

    }

    private void addGame()throws IOException{

    }


    public void initialize(URL localition, ResourceBundle resourceBundle) {
    }
}
