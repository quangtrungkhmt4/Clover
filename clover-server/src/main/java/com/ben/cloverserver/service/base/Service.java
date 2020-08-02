package com.ben.cloverserver.service.base;

import com.ben.cloverserver.model.AbstractModel;

import java.util.List;

public interface Service<T extends AbstractModel> {

    T insertEntity(T t);
    T updateEntity(T t);
    void removeEntity(String id);
    List<T> getAllEntities();

}
