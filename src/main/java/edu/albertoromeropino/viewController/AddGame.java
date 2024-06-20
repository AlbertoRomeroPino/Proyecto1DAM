package edu.albertoromeropino.viewController;

import edu.albertoromeropino.model.dao.CompanyDAO;
import edu.albertoromeropino.model.entity.Company;
import edu.albertoromeropino.model.entity.Game;
import edu.albertoromeropino.model.entity.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private AllGameController controller;

    @Override
    public void onOpen(Object input, Object data) throws IOException {
        this.controller = (AllGameController) input;
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Company> companies = CompanyDAO.build().findAll();
        this.companies = FXCollections.observableArrayList(companies);
        company.setItems(this.companies);

        id.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    id.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void addGame(Event event) {
        Game game = new Game(Integer.parseInt(id.getText()), name.getText(), category.getText(), Person.getPerson(), company.getValue());
        System.out.println(game);
        this.controller.storeGame(game);

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void setController(AllGameController controller) {
        this.controller = controller;
    }
}
