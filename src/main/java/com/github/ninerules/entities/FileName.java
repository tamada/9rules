package com.github.ninerules.entities;

import java.nio.file.Path;
import java.util.Objects;

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

    @Override
    public boolean equals(Object object){
        if(object instanceof FileName){
            return Objects.equals(name, ((FileName)object).name);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
