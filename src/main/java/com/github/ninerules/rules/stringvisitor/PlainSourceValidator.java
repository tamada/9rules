package com.github.ninerules.rules.stringvisitor;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.violations.DefaultViolations;
import com.github.ninerules.rules.violations.EmptyViolations;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.rules.violations.Violations;

public abstract class PlainSourceValidator implements StringLineVisitor, Validator{
    private Violations holder = new DefaultViolations();
    private StrictLevel level;

    public PlainSourceValidator(StrictLevel level){
        this.level = level;
    }

    public StrictLevel level(){
        return level;
    }

    @Override
    public void addViolation(Violation violation) {
        holder.add(violation);
    }

    @Override
    public Results createResults(FileName fileName) {
        return holder.createResults(fileName);
    }

    @Override
    public String preVisitLine(String line, LineCount number){
        updateViolationsIfLineCountainsIgnoreRules(line);
        return line;
    }

    @Override
    public abstract void visitLine(String line, LineCount count);

    private void updateViolationsIfLineCountainsIgnoreRules(String line){
        if(line.contains("@IgnoreRules")){
            holder = new EmptyViolations();
        }
    }
}
