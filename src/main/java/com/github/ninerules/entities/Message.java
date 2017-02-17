package com.github.ninerules.entities;

import java.io.Serializable;
import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Message implements Serializable{
    private static final long serialVersionUID = 1487346776780632708L;

    private String string;

    public Message(String message){
        this.string = message;
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Message
                && checkEquals((Message)object);
    }

    private boolean checkEquals(@NonNull Message message){
        String otherMessage = message.string;
        return Objects.equals(this.string, otherMessage);
    }

    @Override
    public int hashCode(){
        return string.hashCode();
    }

    public String format(Object... args){
        return String.format(string, args);
    }

    @Override
    public String toString(){
        return string;
    }
}
