package com.github.ninerules;

import java.nio.file.Path;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.JdtValidator;
import com.github.ninerules.rules.PlainSourceValidator;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.results.Results;

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

    public Results accept(PlainSourceValidator checker){
        checker.visit(path);
        return checker.createResults(new FileName(path));
    }

    public Results accept(Validator validator){
        if(validator instanceof JdtValidator){
            return accept((JdtValidator)validator);
        }
        return accept((PlainSourceValidator)validator);
    }
}
