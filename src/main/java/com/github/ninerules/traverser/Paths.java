package com.github.ninerules.traverser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Paths {
    private List<Path> pathList = new ArrayList<>();

    public Paths(List<Path> paths){
        this.pathList.addAll(paths);
    }

    Paths(){
    }

    Stream<Path> stream(){
        return pathList.stream();
    }

    void add(Path path){
        pathList.add(path);
    }
}
