package com.github.ninerules.entities;

import java.nio.file.Path;
import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

public class FileName implements Comparable<FileName>{
    private String name;

    public FileName(String name){
        this.name = name;
    }

    public FileName(Path path){
        this(path.toString());
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object object){
        return object instanceof FileName &&
                isEqualsTo((FileName)object);
    }

    private boolean isEqualsTo(@NonNull FileName fileName){
        String otherObjectName = fileName.name;
        return Objects.equals(name, otherObjectName);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public int compareTo(FileName otherName) {
        String name2 = otherName.name;
        return name.compareTo(name2);
    }
}
