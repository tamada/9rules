package com.github.ninerules.traverser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.github.ninerules.utils.ExceptionHandler;

public class Traverser {
    private FileFilter filter;

    public Traverser(){
        this(FileFilter.NULL_FILTER);
    }

    public Traverser(FileFilter filter){
        this.filter = filter;
    }

    public Stream<Path> stream(Path basePath){
        Paths paths = readAll(basePath);

        return paths.stream();
    }

    private Paths readAll(Path basePath){
        return ExceptionHandler.performOrThrows(
                basePath, new Paths(), (path) -> readAllFiles(path));
    }

    private Paths readAllFiles(Path basePath) throws IOException{
        FileVisitor visitor = new FileVisitor(filter);
        Files.walkFileTree(basePath, visitor);
        return visitor.createPaths();
    }
}
