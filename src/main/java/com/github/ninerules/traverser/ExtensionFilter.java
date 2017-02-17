package com.github.ninerules.traverser;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class ExtensionFilter implements FileFilter{
    public static final FileFilter NULL_FILTER = (path, attributes) -> true;

    private String extension;

    public ExtensionFilter(String extension){
        this.extension = extension;
    }

    @Override
    public boolean accept(Path path, BasicFileAttributes attributes) {
        return filterByExtension(path.toString());
    }

    private boolean filterByExtension(String fileName){
        return fileName.endsWith(extension);
    }
}
