package com.github.nine.traverser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PathList {
    private List<Path> paths;

    public PathList(List<Path> paths){
        this.paths.addAll(paths);
    }

    PathList(){
        paths = new ArrayList<>();
    }

    Stream<Path> stream(){
        return paths.stream();
    }

    void add(Path path){
        paths.add(path);
    }
}
