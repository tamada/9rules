package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.ninerules.entities.FileName;

public abstract class ViolationChecker extends ASTVisitorPlus{
    private List<Violation> violations = new ArrayList<>();

    public void addViolation(Violation violation){
        violations.add(violation);
    }

    public final Results createResults(FileName fileName){
        return new Results(fileName, violations);
    }
}
