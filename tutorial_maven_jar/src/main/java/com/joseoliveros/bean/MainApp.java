package com.joseoliveros.bean;

import com.joseoliveros.messenger.dao.DaoException;
import com.joseoliveros.messenger.dao.mysql.MySqlMessageDao;
import com.joseoliveros.messenger.model.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainApp {
    
    public static void main(String[] args) throws DaoException {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MySqlMessageDao mySqlMessageDao = (MySqlMessageDao) context.getBean("messageDao");
        List<Message> messageList = mySqlMessageDao.obtenerTodos();
        for (Message message : messageList) {
            System.out.println(message.toString());
        }
    }
}
