package com.github.ninerules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import io.vavr.control.Try;

public class SourceParser {
    private Path path;

    public SourceParser(Path path){
        this.path = path;
    }

    public String parse(){
        return readSource(path);
    }

    private String readSource(Path path){
        return Try.of(() -> readPlainSource(path))
                .getOrElse("");
    }

    private String readPlainSource(Path path) throws IOException{
        return Files.lines(path)
                .collect(Collectors.joining("\n"));
    }
}
