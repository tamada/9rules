package com.github.ninerules.rules;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.entities.LineCount;

public abstract class ASTVisitorPlus extends ASTVisitor{
    private CompilationUnit unit;

    @Override
    public boolean visit(CompilationUnit node) {
        this.unit = node;
        return super.visit(node);
    }

    public LineCount startLine(ASTNode node){
        int startPosition = node.getStartPosition();
        int line = unit.getLineNumber(startPosition);
        return new LineCount(line);
    }

    public LineCount countLinesOf(ASTNode node){
        int line = startLineNumber(node);
        int last = endLineNumber(node);
        return new LineCount(last - line);
    }

    private int startLineNumber(ASTNode node){
        int start = node.getStartPosition();
        return unit.getLineNumber(start);
    }

    private int endLineNumber(ASTNode node){
        int startPosition = node.getStartPosition();
        int lastPosition = startPosition + node.getLength() - 1;
        return unit.getLineNumber(lastPosition);
    }
}
