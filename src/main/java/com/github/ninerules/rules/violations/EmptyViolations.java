package com.github.ninerules.rules.violations;

import java.util.ArrayList;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.results.Results;

public class EmptyViolations implements Violations{
    @Override
    public void add(Violation violation){
        // do nothing because this object has no violations.
    }

    @Override
    public Results createResults(FileName fileName){
        return new Results(fileName, new ArrayList<>());
    }    
}
