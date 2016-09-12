package com.github.ninerules.rules;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.results.Results;

public abstract class JdtValidator extends ASTVisitorPlus implements Validator{
    private ViolationHolder holder = new ViolationHolder();
    private StrictLevel level;

    public JdtValidator(StrictLevel level){
        this.level = level;
    }

    @Override
    public StrictLevel level(){
        return level;
    }

    @Override
    public void addViolation(Violation violation){
        holder.addViolation(violation);
    }

    @Override
    public final Results createResults(FileName fileName){
        return holder.createResults(fileName);
    }

    public Parameter parameter(){
        return Parameters.parameter(parameterClass(), level());
    }

    public Class<? extends Parameter> parameterClass(){
        return NullParameter.class;
    }
}
