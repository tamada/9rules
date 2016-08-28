package com.github.nine.rules;

import java.io.Serializable;

public class ViolationType implements Serializable{
    private static final long serialVersionUID = -846665473550557939L;

    private String message;

    public ViolationType(String message){
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }
}
