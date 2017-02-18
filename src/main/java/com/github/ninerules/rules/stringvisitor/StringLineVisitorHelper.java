package com.github.ninerules.rules.stringvisitor;

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
        }
        catch (IOException e){
            // do nothing for ignoring exception.
        }
    }

    private void visitImpl(Path path) throws IOException{
        try(Stream<String> stream = Files.lines(path)){
            visitLine(stream);
        }
    }

    private void visitLine(Stream<String> stream) throws IOException{
        Streams.zip(stream, generate())
        .forEach(this::visitLine);
    }

    private void visitLine(Pair<String, LineCount> pair){
        String line = pair.left();
        LineCount count = pair.right();
        callVisitorLineInVisitor(line, count);
    }

    private void callVisitorLineInVisitor(String line, LineCount count){
        String convertedLine = visitor.preVisitLine(line, count);
        visitor.visitLine(convertedLine, count);
    }

    private Stream<LineCount> generate(){
        return Stream.iterate(1, x -> x + 1)
                .map(LineCount::new);        
    }
}
