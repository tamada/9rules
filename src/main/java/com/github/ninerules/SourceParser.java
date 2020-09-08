package com.github.ninerules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import com.github.ninerules.utils.Either;
import com.github.ninerules.utils.ExceptionHandler;

public class SourceParser {
    private Path path;

    public SourceParser(Path path){
        this.path = path;
    }

    public String parse() {
        return readSource(path)
                .value()
                .orElse("");
    }

    private Either<IOException, String> readSource(Path path){
        try{
            return Either.ofValue(readPlainSource(path));
        } catch(IOException e){
            return Either.ofException(e);
        }
    }

    private String readPlainSource(Path path) throws IOException{
        return Files.lines(path)
                .collect(Collectors.joining("\n"));
    }
}
