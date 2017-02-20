package com.github.ninerules.cli;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Argument {
    private String givenArgument;

    public Argument(String argument){
        this.givenArgument = argument;
    }

    public Path toPath(){
        return Paths.get(givenArgument);
    }
    @Override
    public int hashCode(){
        return givenArgument.hashCode();
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Argument
                && isEqualsTo((Argument)object);
    }

    private boolean isEqualsTo(@NonNull Argument arg){
        String name = arg.givenArgument;
        return Objects.equals(givenArgument, name);
    }
}
