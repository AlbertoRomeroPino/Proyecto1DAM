package edu.albertoromeropino.model.interfaces;

import java.io.Closeable;

public interface IDAO<T, K> extends Closeable {    //Closeable es que se cierra una vez termine
    T store(T entity);

    T findID(K entityId);

    T deleteEntity(T entityDelete);

}
