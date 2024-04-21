module edu.albertoromeropino {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.albertoromeropino to javafx.fxml;
    exports edu.albertoromeropino;
}
