package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.model.dao.GameDAO;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.viewController.enums.Scenes;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AllGameController extends Controller implements Initializable {

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
    public void onOpen(Object input, Object data) throws IOException {
        List<Game> games = GameDAO.build().findAll();
        this.games = FXCollections.observableArrayList(games);
        tableView.setItems(this.games);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        columnIdGame.setCellValueFactory(game -> new SimpleIntegerProperty(game.getValue().getIdGame()).asObject());
        columnName.setCellValueFactory(game -> new SimpleStringProperty(game.getValue().getName()));
        columnCategory.setCellValueFactory(game -> new SimpleStringProperty(game.getValue().getCategory()));
        columnPerson.setCellValueFactory(game -> new SimpleStringProperty(game.getValue().getPerson().getNickName()));
        columnCompany.setCellValueFactory(game -> new SimpleStringProperty(game.getValue().getCompany().getNameCompany()));
    }

    @FXML
    public void addGame(Event event) throws IOException {
        App.currentController.openModal(Scenes.ADDGAME, "Agregar un juego", this, null);
    }

    @FXML
    public void deleteGame(Event event) throws IOException {
        GameDAO.build().deleteEntity(tableView.getSelectionModel().getSelectedItem());
        this.games.remove(tableView.getSelectionModel().getSelectedItem());
    }

    public void storeGame(Game newGame){
        GameDAO.build().store(newGame);
        this.games.add(newGame);
    }

}