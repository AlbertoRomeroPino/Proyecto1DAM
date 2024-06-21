package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.model.dao.GameDAO;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import edu.albertoromeropino.model.entity.Sesion;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
    private TableColumn<Game, String> columnCompany;

    private ObservableList<Game> games;


    @Override
    public void onOpen(Object input, Object data) throws IOException {
        List<Game> games = GameDAO.build().findByPerson(Sesion.getSesion().getPerson().getNickName());
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
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCategory.setCellValueFactory(game -> new SimpleStringProperty(game.getValue().getCategory()));
        columnCategory.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCompany.setCellValueFactory(game -> new SimpleStringProperty(game.getValue().getCompany().getNameCompany()));

        columnName.setOnEditCommit(event -> {
            if(event.getNewValue() == event.getOldValue()){
                return;
            }

            if(event.getNewValue().length()<=200){
                Game game = event.getRowValue();
                game.setName(event.getNewValue());
                GameDAO.build().store(game);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error");
                alert.show();
            }
            //Actualizar los datos



        });
        columnCategory.setOnEditCommit(event -> {
            if(event.getNewValue() == event.getOldValue()){
                return;
            }

            if(event.getNewValue().length()<=200){
                Game game = event.getRowValue();
                game.setName(event.getNewValue());
                GameDAO.build().store(game);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error");
                alert.show();
            }
        });
    }

    @FXML
    public void findArchievement(Event event) throws IOException{
       App.setRoot(Scenes.ARCHIEVEMENT, tableView.getSelectionModel().getSelectedItem().getIdGame());
    }
}
