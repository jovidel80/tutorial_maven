package com.joseoliveros.bean;

public class HelloWorld {
    
    private String message;

    public HelloWorld(String name) {
        this.message = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String  getMessage() {
        return this.message;
    }
}
