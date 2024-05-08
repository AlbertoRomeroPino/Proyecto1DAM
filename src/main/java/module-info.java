module edu.albertoromeropino {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires java.desktop;
    requires org.checkerframework.checker.qual;

    opens edu.albertoromeropino to javafx.fxml;
    opens edu.albertoromeropino.model.connection to java.xml.bind;

    exports edu.albertoromeropino;
    exports edu.albertoromeropino.viewController;
    opens edu.albertoromeropino.viewController to javafx.fxml;
    exports edu.albertoromeropino.viewController.enums;
    opens edu.albertoromeropino.viewController.enums to javafx.fxml;

}
