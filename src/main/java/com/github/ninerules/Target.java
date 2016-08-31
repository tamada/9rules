package com.github.ninerules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.rules.JdtValidator;
import com.github.ninerules.rules.PlainSourceValidator;
import com.github.ninerules.rules.Results;
import com.github.ninerules.rules.Validator;

public class Target {
    private Path path;
    private CompilationUnit unit;

    public Target(Path path, CompilationUnit unit){
        this.path = path;
        this.unit = unit;
    }

    public Results accept(JdtValidator checker){
        unit.accept(checker);
        return checker.createResults(new FileName(path));
    }

    public Results accept(Validator validator){
        if(validator instanceof JdtValidator){
            return accept((JdtValidator)validator);
        }
        return accept((PlainSourceValidator)validator);
    }

    public Results accept(PlainSourceValidator checker){
        try(Stream<String> stream = Files.lines(path)){
            visitLine(stream, checker);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return checker.createResults(new FileName(path));
    }

    private void visitLine(Stream<String> stream, PlainSourceValidator checker) throws IOException{
        int count = 1;
        for(Iterator<String> i = stream.iterator(); i.hasNext(); ){
            String string = i.next();
            checker.visit(string, new LineCount(count));
            count++;
        }
    }
}
