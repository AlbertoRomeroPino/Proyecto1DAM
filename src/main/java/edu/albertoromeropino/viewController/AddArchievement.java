package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.dao.GameDAO;
import edu.albertoromeropino.model.entity.Archievement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddArchievement extends Controller implements Initializable {

    @FXML
    private TextField idArchievement;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField help;
    private int idGame;
    private ArchievementController controller;

    @Override
    public void onOpen(Object input) throws IOException {
        idGame = (Integer) input;
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idArchievement.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    idArchievement.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    @FXML
    private void addArchievement(ActionEvent event) {
        System.out.println(Integer.parseInt(idArchievement.getText()));
        System.out.println(GameDAO.build().findID(idGame));
        Archievement archievement = new Archievement(Integer.parseInt(idArchievement.getText()), name.getText(),
                description.getText(), help.getText(), GameDAO.build().findID(idGame));
        this.controller.storeArchievement(archievement);
    }

}
