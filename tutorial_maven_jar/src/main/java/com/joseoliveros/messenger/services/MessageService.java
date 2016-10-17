package com.joseoliveros.messenger.services;

import com.joseoliveros.messenger.dao.DaoException;
import com.joseoliveros.messenger.dao.mysql.MySqlMessageDao;
import com.joseoliveros.messenger.exceptions.DataNotFoundException;
import com.joseoliveros.messenger.model.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MessageService {

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    MySqlMessageDao mySqlMessageDao = (MySqlMessageDao) context.getBean("messageDao");

    public List<Message> getAllMessages() throws DaoException {
        return mySqlMessageDao.obtenerTodos();
    }

    public Message getMessage(Long messageId) throws DaoException {
        Message message = mySqlMessageDao.obtener(messageId);
        if (message == null) {
            throw new DataNotFoundException("Message with id " + messageId + " not found.");
        }
        return message;
    }

    public Message addMessage(Message message) throws DaoException {
        return mySqlMessageDao.crear(message);
    }

    public Message updateMessage(Message message) throws DaoException {
        if (message.getId() <= 0) {
            return null;
        }
        return mySqlMessageDao.actualizar(message.getId(), message);
    }

    public boolean deleteMessage(Long messageId) throws DaoException {
        return mySqlMessageDao.eliminar(messageId);
    }
}
