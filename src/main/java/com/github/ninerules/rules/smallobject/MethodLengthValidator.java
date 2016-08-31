package com.github.ninerules.rules.smallobject;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class MethodLengthValidator extends Validator {
    public static final ViolationType TOO_LONG_METHOD = new ViolationType("method is too long (over 5 lines).");
    private static final LineCount MAX_METHOD_LENGTH = new LineCount(7);

    @Override
    public void endVisit(MethodDeclaration node){
        LineCount difference = countLinesOf(node);
        checkViolationOfMethodLength(node, difference);
        super.endVisit(node);
    }

    private void checkViolationOfMethodLength(ASTNode node, LineCount difference){
        LineCount number = startLine(node);
        if(difference.isGreaterThan(MAX_METHOD_LENGTH)){
            this.addViolation(new Violation(TOO_LONG_METHOD, new LineCounts(number)));
        }
    }
}
