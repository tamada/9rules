package com.github.ninerules.rules.elsestatement;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;

import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.rules.JdtValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class NoElseStatementValidator extends JdtValidator {
    public static final ViolationType NO_ELSE_STATEMENT = new ViolationType("else statement found.");

    @Override
    public boolean visit(IfStatement node){
        checkViolation(node);
        return super.visit(node);
    }

    private void checkViolation(IfStatement node){
        Statement statement = node.getElseStatement();
        if(isViolation(statement)){
            addViolation(new Violation(NO_ELSE_STATEMENT, new LineCounts(startLine(node))));
        }
    }

    private boolean isViolation(Statement statement){
        return statement != null &&
                statement.getNodeType() != ASTNode.IF_STATEMENT;        
    }
}
