package edu.albertoromeropino.model.test;

import edu.albertoromeropino.utils.ManagerXML;
import edu.albertoromeropino.model.connection.ConnectionProperties;

public class ConnectionSave {
    public static void main(String[] arg) {
        ConnectionProperties connection = new ConnectionProperties
                ("localhost", "3306", "ScoreChest", "root", "root");
        ManagerXML.writeXML(connection, "connection.xml");
    }
}
