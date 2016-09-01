package com.github.ninerules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class SourceParser {
    private Path path;

    public SourceParser(Path path){
        this.path = path;
    }

    public String parse(){
        return readSource(path);
    }

    private String readSource(Path path){
        try {
            return Files.lines(path)
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
        }
        return "";
    }
}
