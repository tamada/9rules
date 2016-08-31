package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.rules.results.Results;

public abstract class PlainSourceValidator implements Validator{
    private List<Violation> violations = new ArrayList<>();

    public abstract void visit(String line, LineCount count);

    @Override
    public void addViolation(Violation violation){
        violations.add(violation);
    }

    @Override
    public final Results createResults(FileName fileName){
        return new Results(fileName, violations);
    }    
}
