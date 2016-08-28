package com.github.nine;

import java.nio.file.Path;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.nine.entities.FileName;
import com.github.nine.rules.Results;
import com.github.nine.rules.ViolationChecker;

public class Target {
    private Path path;
    private CompilationUnit unit;

    public Target(Path path, CompilationUnit unit){
        this.path = path;
        this.unit = unit;
    }

    public Results accept(ViolationChecker checker){
        unit.accept(checker);
        return checker.createResults(new FileName(path));
    }
}
