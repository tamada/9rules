package com.github.ninerules.rules.jdtvisitor;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.Validator;

public abstract class LevelSwitchableValidator extends ASTVisitorPlus implements Validator{
    private StrictLevel level;

    public LevelSwitchableValidator(StrictLevel level){
        this.level = level;
    }

    @Override
    public StrictLevel level(){
        return level;
    }

    public Parameter parameter(){
        return Parameters.parameter(parameterClass(), level());
    }

    public Class<? extends Parameter> parameterClass(){
        return NullParameter.class;
    }
}
