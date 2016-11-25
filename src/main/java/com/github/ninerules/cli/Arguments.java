package com.github.ninerules.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Arguments {
    private List<Argument> arguments = new ArrayList<>();

    public Arguments(Stream<Argument> stream){
        stream.forEach(item -> {
            arguments.add(item);   
        });
    }

    public boolean isEmpty(){
        return arguments.isEmpty();
    }

    Stream<Argument> stream(){
        return arguments.stream();
    }
}
