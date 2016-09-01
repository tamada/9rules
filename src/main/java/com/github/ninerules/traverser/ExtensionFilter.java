package com.github.ninerules.traverser;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class ExtensionFilter implements FileFilter{
    private String extension;

    public ExtensionFilter(String extension){
        this.extension = extension;
    }

    @Override
    public boolean accept(Path path, BasicFileAttributes attributes) {
        if(attributes.isDirectory()){
            return true;
        }
        return extensionFilter(path.toString());
    }

    private boolean extensionFilter(String fileName){
        return fileName.endsWith(extension);
    }
}
