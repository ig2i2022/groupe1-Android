package com.example.sel.dao;


import com.example.sel.exception.SELException;


import java.util.List;

public interface Dao<T> {

    void create(T object);

    T get(int id) throws SELException;

    List<T> getAll() throws SELException, SELException;

    void delete(int id);

    void update(T object);
}
