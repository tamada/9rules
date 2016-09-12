package com.github.ninerules.entities;

import java.util.Objects;

public class Message {
    private String message;

    public Message(String message){
        this.message = message;
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Message
                && equals((Message)object);
    }

    private boolean equals(Message message){
        String otherMessage = message.message;
        return Objects.equals(this.message, otherMessage);
    }

    @Override
    public int hashCode(){
        return message.hashCode();
    }

    public String format(Object... args){
        return String.format(message, args);
    }

    @Override
    public String toString(){
        return message;
    }
}
