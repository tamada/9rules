package com.github.ninerules.traverser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Traverser {
    private FileFilter filter;

    public Traverser(){
        this(null);
    }

    public Traverser(FileFilter filter){
        this.filter = filter;
    }

    public Stream<Path> stream(Path basePath){
        PathList paths = readAll(basePath);

        return paths.stream();
    }

    private PathList readAll(Path basePath){
        try {
            return readAllFiles(basePath);
        } catch (IOException e) {
        }
        return null;
    }

    private PathList readAllFiles(Path basePath) throws IOException{
        FileVisitor visitor = new FileVisitor(filter);
        Files.walkFileTree(basePath, visitor);

        return visitor.createPaths();
    }
}
