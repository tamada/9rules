package com.github.ninerules.traverser;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;



public class Walker {
    private FileVisitor<Path> visitor;

    public Walker(FileVisitor<Path> visitor){
        this.visitor = visitor;
    }

    public void accept(Path startPoint) throws IOException{
        Files.walkFileTree(startPoint, visitor);
    }

    public void accept(FileSystem system, Path startPoint) throws IOException{
        FileSystemProvider provider = system.provider();
        walk(provider, system, startPoint);
    }

    private void walk(FileSystemProvider provider, FileSystem system, Path startPoint) throws IOException{
        DirectoryStream<Path> stream = provider.newDirectoryStream(startPoint, (entry) -> true);

    }

    private BasicFileAttributes readAttributes(Path path, FileSystemProvider provider) throws IOException{
        return provider.readAttributes(path, BasicFileAttributes.class);
    }
}
