package com.github.ninerules.rules.violations;

import java.util.ArrayList;
import java.util.List;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.results.Results;

public class DefaultViolations extends EmptyViolations{
    private List<Violation> violations = new ArrayList<>();

    @Override
    public void add(Violation violation){
        violations.add(violation);
    }

    @Override
    public Results createResults(FileName fileName){
        return new Results(fileName, violations);
    }    
}
