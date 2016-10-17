package com.joseoliveros.messenger.dao.util;

import com.joseoliveros.messenger.dao.DaoException;
import com.joseoliveros.messenger.model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MessageDaoUtil {
    
    public static Message convertir(ResultSet rs) throws SQLException, DaoException {
        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setMessage(rs.getString("message"));
        message.setAuthor(rs.getString("author"));
        message.setCreated(rs.getString("created"));
        return message;
    }
}
