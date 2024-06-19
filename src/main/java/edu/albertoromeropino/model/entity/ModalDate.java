package edu.albertoromeropino.model.entity;

import edu.albertoromeropino.viewController.Controller;
import javafx.scene.Parent;

public class ModalDate {
    private Controller parent;
    private Object date;



    public ModalDate(Controller parent, Object date) {
        this.parent = parent;
        this.date = date;
    }

    public Controller getParent() {
        return parent;
    }

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
