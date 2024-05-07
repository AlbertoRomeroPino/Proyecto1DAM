package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.dao.GameDAO;
import edu.albertoromeropino.model.entity.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameController extends Controller{
    @FXML
    private TableView<Game> tableView;

    @FXML
    private TableColumn<Game, String> columnIdGame;
    private TableColumn<Game, String> columnName;
    private TableColumn<Game, String> columnCategory;
    private TableColumn<Game, String> columnPerson;
    private TableColumn<Game, String> columnCompany;

    @FXML
    private BorderPane borderPane;

    @Override
    public void openTab(Object input) throws IOException {

    }

    @Override
    public void addNew() throws IOException {

    }

    @Override
    public void initialize(URL localition, ResourceBundle resourceBundle) {

    }
}
