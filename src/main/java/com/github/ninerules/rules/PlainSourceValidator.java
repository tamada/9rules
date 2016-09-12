package com.github.ninerules.rules;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.rules.results.Results;

public abstract class PlainSourceValidator implements StringLineVisitor, Validator{
    private ViolationHolder holder = new ViolationHolder();
    private StrictLevel level;

    public PlainSourceValidator(StrictLevel level){
        this.level = level;
    }

    public StrictLevel level(){
        return level;
    }

    @Override
    public void addViolation(Violation violation) {
        holder.addViolation(violation);
    }

    @Override
    public Results createResults(FileName fileName) {
        return holder.createResults(fileName);
    }

    @Override
    public abstract void visitLine(String line, LineCount count);
}
