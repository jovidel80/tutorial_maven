package com.joseoliveros.messenger.model;

import com.joseoliveros.messenger.dao.DaoException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@XmlRootElement
@XmlType(propOrder = {"id", "message", "author", "created"})
public class Message {
    
    private Long id;
    private String message;
    private String author;
    private Date created;

    public Message() {
        
    }

    public Message(String message, String author) {
        this.message = message;
        this.author = author;
        this.created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(String created) throws DaoException {
        try {
            Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(created);
            this.created = date;
        } catch (ParseException e) {
            throw new DaoException("Error en SQL", e);
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", author='" + author + '\'' +
                ", created=" + created +
                '}';
    }
}
