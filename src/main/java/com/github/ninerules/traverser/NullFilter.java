package com.github.ninerules.traverser;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class NullFilter implements FileFilter{
    public static final FileFilter INSTANCE = new NullFilter();

    private NullFilter(){
    }

    @Override
    public boolean accept(Path path, BasicFileAttributes attributes) {
        return true;
    }
}
