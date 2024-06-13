package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.dao.ArchievementDAO;
import edu.albertoromeropino.model.dao.GameDAO;
import edu.albertoromeropino.model.entity.Archievement;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import javax.swing.text.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ArchievementController extends Controller implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Game, Integer> columnIdArchievement;
    @FXML
    private TableColumn<Game, String> columnName;
    @FXML
    private TableColumn<Game, String> columnDescription;
    @FXML
    private TableColumn<Game, String> columnHelp;

    @Override
    public void onOpen(Object imput) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
