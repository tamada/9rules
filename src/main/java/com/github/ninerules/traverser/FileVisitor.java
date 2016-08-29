package com.github.ninerules.traverser;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitor extends SimpleFileVisitor<Path>{
    private PathList paths = new PathList();
    private FileFilter filter;

    public FileVisitor(FileFilter filter){
        if(filter == null){
            filter = FileFilter.NULL_FILTER;
        }
        this.filter = filter;
    }

    public FileVisitor(){
        this(FileFilter.NULL_FILTER);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        if(filter.accept(file, attributes)){
            paths.add(file);
        }
        return super.visitFile(file, attributes);
    }

    PathList createPaths(){
        return paths;
    }
}
