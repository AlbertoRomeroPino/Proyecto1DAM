package edu.albertoromeropino.model.interfaces;

import edu.albertoromeropino.model.entity.Game;

import java.io.Closeable;

public interface IDAO<T> extends Closeable {    //Closeable es que se cierra una vez termine
    T save(T entity);
    T findID(int entityId);
    T deleteEntity (T entityDelete);

}
