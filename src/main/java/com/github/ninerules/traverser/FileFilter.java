package com.github.ninerules.traverser;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Consumer;

@FunctionalInterface
public interface FileFilter {
    FileFilter NULL_FILTER = (path, attributes) -> true;

    boolean accept(Path path, BasicFileAttributes attributes);

    default void ifAccept(Path path, BasicFileAttributes attributes, Consumer<Path> consumer){
        if(accept(path, attributes)){
            consumer.accept(path);
        }
    }
}
