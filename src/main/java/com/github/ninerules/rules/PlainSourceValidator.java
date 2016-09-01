package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.rules.results.Results;

public abstract class PlainSourceValidator implements Validator, StringLineVisitor{
    private List<Violation> violations = new ArrayList<>();

    @Override
    public abstract void visitLine(String line, LineCount count);

    @Override
    public void addViolation(Violation violation){
        violations.add(violation);
    }

    @Override
    public final Results createResults(FileName fileName){
        return new Results(fileName, violations);
    }    
}
