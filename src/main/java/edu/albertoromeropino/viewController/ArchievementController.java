package edu.albertoromeropino.viewController;

import edu.albertoromeropino.App;
import edu.albertoromeropino.model.dao.ArchievementDAO;
import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.viewController.enums.Scenes;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ArchievementController extends Controller implements Initializable {
    @FXML
    private TableView<Archievement> tableView;
    @FXML
    private TableColumn<Archievement, Integer> columnIdArchievement;
    @FXML
    private TableColumn<Archievement, String> columnName;
    @FXML
    private TableColumn<Archievement, String> columnDescription;
    @FXML
    private TableColumn<Archievement, String> columnHelp;

    private ObservableList<Archievement> archievements;
    private int idGame;

    @Override
    public void onOpen(Object input) throws IOException {
        idGame =(Integer) input;
        List<Archievement> archievements = ArchievementDAO.build().findByIdGame((Integer) input);
        this.archievements = FXCollections.observableArrayList(archievements);
        tableView.setItems(this.archievements);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        columnIdArchievement.setCellValueFactory(archievement -> new SimpleIntegerProperty(archievement.getValue().getIdArchievement()).asObject());
        columnName.setCellValueFactory(archievement -> new SimpleStringProperty(archievement.getValue().getArchievementName()));
        columnDescription.setCellValueFactory(archievement -> new SimpleStringProperty(archievement.getValue().getDescriptionArchievement()));
        columnHelp.setCellValueFactory(archievement -> new SimpleStringProperty(archievement.getValue().getHelpArchievement()));

    }

    @FXML
    private void goToMenuBar() throws IOException {
        App.setRoot(Scenes.MENUBAR, null);
    }
@FXML
    public void addArchievement(ActionEvent event) throws IOException{
       App.currentController.openModal(Scenes.ADDARCHIEVEMENT, "Agregar un logro", this, idGame);
    }

    public void storeArchievement(Archievement newArchievement){
        ArchievementDAO.build().store(newArchievement);
        this.archievements.add(newArchievement);
    }

    @FXML
    private void deleteArchievement(ActionEvent event) throws IOException{
        ArchievementDAO.build().deleteEntity(tableView.getSelectionModel().getSelectedItem());
        this.archievements.remove(tableView.getSelectionModel().getSelectedItem());
    }


}
