module edu.albertoromeropino {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;

    opens edu.albertoromeropino to javafx.fxml;
    opens edu.albertoromeropino.model.connection to java.xml.bind;

    exports edu.albertoromeropino;

}
