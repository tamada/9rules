package com.github.ninerules.rules.elsestatement;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.rules.JdtValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class NoElseStatementValidator extends JdtValidator<NullParameter> {
    public static final ViolationType NO_ELSE_STATEMENT = new ViolationType("else statement found.");

    public NoElseStatementValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public boolean visit(IfStatement node){
        checkViolation(node);
        return super.visit(node);
    }

    private void checkViolation(IfStatement node){
        Statement statement = node.getElseStatement();
        if(isViolation(Optional.ofNullable(statement))){
            addViolation(new Violation(NO_ELSE_STATEMENT, new LineCounts(startLine(node))));
        }
    }

    private boolean isViolation(Optional<Statement> item){
        return item.map(statement -> {
            int nodeType = statement.getNodeType();
            return nodeType != ASTNode.IF_STATEMENT;        
        }).orElse(false);
    }

    @Override
    public Parameter<NullParameter> parameter() {
        return NullParameter.parameter();
    }
}
