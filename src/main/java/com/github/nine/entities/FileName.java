package com.github.nine.entities;

import java.nio.file.Path;

public class FileName {
    private String name;

    public FileName(String name){
        this.name = name;
    }

    public FileName(Path path){
        this(path.toString());
    }

    public String toString(){
        return name;
    }
}
