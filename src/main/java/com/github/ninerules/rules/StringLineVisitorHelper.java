package com.github.ninerules.rules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.github.ninerules.entities.LineCount;
import com.github.ninerules.utils.Pair;
import com.github.ninerules.utils.Streams;

public class StringLineVisitorHelper {
    private StringLineVisitor visitor;

    public StringLineVisitorHelper(StringLineVisitor visitor){
        this.visitor = visitor;
    }

    public void visit(Path path){
        try {
            visitImpl(path);
        } catch (IOException e) { }
    }

    private void visitImpl(Path path) throws IOException{
        try(Stream<String> stream = Files.lines(path)){
            visitLine(stream);
        }
    }

    private void visitLine(Stream<String> stream) throws IOException{
        Streams.zip(stream, generate())
        .forEach(pair -> visitLine(pair));
    }

    private void visitLine(Pair<String, LineCount> pair){
        String line = pair.left();
        LineCount count = pair.right();
        visitor.visitLine(line, count);
    }

    private Stream<LineCount> generate(){
        return Stream.iterate(1, x -> x + 1)
                .map(index -> new LineCount(index));        
    }
}
