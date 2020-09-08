package com.github.ninerules.rules.elsestatement;

import static org.eclipse.jdt.core.dom.ASTNode.IF_STATEMENT;

import java.util.Optional;

import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.Message;
import com.github.ninerules.rules.jdtvisitor.JdtValidator;

public class NoElseStatementValidator extends JdtValidator {
    public static final Message NO_ELSE_STATEMENT = new Message("else statement found.");

    public NoElseStatementValidator() {
        this(StrictLevel.STRICT);
    }

    public NoElseStatementValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public boolean visit(IfStatement node){
        checkViolation(node);
        return super.visit(node);
    }

    private void checkViolation(IfStatement node){
        checkViolation(node, node.getElseStatement());
    }

    private void checkViolation(IfStatement node, Statement statement){
        if(isViolation(Optional.ofNullable(statement))){
            addViolation(buildViolation(NO_ELSE_STATEMENT, new LineCounts(startLine(node))));
        }
    }

    private boolean isViolation(Optional<Statement> item){
        return item
                .map(statement -> statement.getNodeType() != IF_STATEMENT)
                .orElse(false);        
    }
}
