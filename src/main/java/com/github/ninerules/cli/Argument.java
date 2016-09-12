package com.github.ninerules.cli;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Argument {
    private String argument;

    public Argument(String argument){
        this.argument = argument;
    }

    public Path toPath(){
        return Paths.get(argument);
    }

    @Override
    public int hashCode(){
        return argument.hashCode();
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Argument
                && equals((Argument)object);
    }

    private boolean equals(Argument arg){
        String name = arg.argument;
        return Objects.equals(argument, name);
    }
}
