package com.github.nine.rules;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.nine.entities.LineNumber;

public abstract class ASTVisitorPlus extends ASTVisitor{
    private CompilationUnit unit;

    @Override
    public boolean visit(CompilationUnit node) {
        this.unit = node;
        return super.visit(node);
    }

    public LineNumber getLineNumber(int position){
        int line = unit.getLineNumber(position);
        return new LineNumber(line);
    }
}
