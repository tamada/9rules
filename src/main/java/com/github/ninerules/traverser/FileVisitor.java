package com.github.ninerules.traverser;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

class FileVisitor extends SimpleFileVisitor<Path>{
    private Paths paths = new Paths();
    private FileFilter filter;

    public FileVisitor(FileFilter filter){
        Optional<FileFilter> optionalFilter = Optional.ofNullable(filter);
        this.filter = optionalFilter
                .orElse(FileFilter.NULL_FILTER);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        filter.ifAccept(file, attributes,
                (path) -> paths.add(path));
        return super.visitFile(file, attributes);
    }

    Paths createPaths(){
        return paths;
    }
}
