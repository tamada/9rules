package com.github.ninerules.traverser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Paths {
    private List<Path> paths;

    public Paths(List<Path> paths){
        this.paths = new ArrayList<>();
        this.paths.addAll(paths);
    }

    Paths(){
        paths = new ArrayList<>();
    }

    Stream<Path> stream(){
        return paths.stream();
    }

    void add(Path path){
        paths.add(path);
    }
}
