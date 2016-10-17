package com.joseoliveros.messenger.dao.util;

import com.joseoliveros.messenger.dao.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoUtil<T> {

    T convertir(ResultSet resultSet) throws SQLException, DaoException;
}
