package com.github.ninerules.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Arguments {
    private List<Argument> list = new ArrayList<>();

    public Arguments(Stream<Argument> stream){
        stream.forEach(list::add);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    Stream<Argument> stream(){
        return list.stream();
    }
}
