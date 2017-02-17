package com.github.ninerules.rules.jdtvisitor;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.Validator;

public abstract class ASTVisitorPlus extends ASTVisitor implements Validator{
    private LineCountCalculator calculator;
    private StrictLevel level;

    public ASTVisitorPlus(StrictLevel level){
        this.level = level;
    }

    @Override
    public StrictLevel level(){
        return level;
    }

    @Override
    public Parameter parameter(){
        return Parameters.parameter(parameterClass(), level());
    }

    public Class<? extends Parameter> parameterClass(){
        return NullParameter.class;
    }
    
    @Override
    public boolean visit(CompilationUnit node) {
        this.calculator = new LineCountCalculator(node);
        return super.visit(node);
    }

    public LineCount startLine(ASTNode node){
        return calculator.startLine(node);
    }

    public LineCount countLinesOf(ASTNode node){
        return calculator.countLinesOf(node);
    }
}
