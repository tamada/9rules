package com.github.ninerules;

import java.nio.file.Path;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.Results;
import com.github.ninerules.rules.Validator;

public class Target {
    private Path path;
    private CompilationUnit unit;

    public Target(Path path, CompilationUnit unit){
        this.path = path;
        this.unit = unit;
    }

    public Results accept(Validator checker){
        unit.accept(checker);
        return checker.createResults(new FileName(path));
    }
}
