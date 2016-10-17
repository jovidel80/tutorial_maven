package com.joseoliveros.messenger.dao;

import java.util.List;

public interface DAO<T, K> {

    List<T> obtenerTodos() throws DaoException;

    T obtener(K id) throws DaoException;

    T crear(T t) throws DaoException;

    T actualizar(K id, T t) throws DaoException;

    boolean eliminar(K id) throws DaoException;
}
