package edu.albertoromeropino.model.test;

import edu.albertoromeropino.model.Utils.ManagerXML;
import edu.albertoromeropino.model.connection.ConnectionProperties;

public class connectionSave {
    public static void main(String[] arg){
        ConnectionProperties connection = new ConnectionProperties
                ("localhost","3306","archievement","root","root");
        ManagerXML.writeXML(connection,"connection.xml");
    }
}
