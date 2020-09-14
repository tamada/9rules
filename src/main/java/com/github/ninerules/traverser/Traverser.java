package com.github.ninerules.traverser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import io.vavr.control.Try;

public class Traverser {
    private FileFilter filter;

    public Traverser(){
        this(NullFilter.INSTANCE);
    }

    public Traverser(FileFilter filter){
        this.filter = filter;
    }

    public Stream<Path> stream(Path basePath){
        Paths paths = readAll(basePath);
        return paths.stream();
    }

    private Paths readAll(Path basePath){
        return Try.of(() -> readAllFiles(basePath))
                .getOrElse(Paths::new);
    }

    private Paths readAllFiles(Path basePath) throws IOException{
        FileVisitor visitor = new FileVisitor(filter);
        Files.walkFileTree(basePath, visitor);
        return visitor.createPaths();
    }
}
