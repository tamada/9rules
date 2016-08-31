package com.github.ninerules.rules.smallobject;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class SourceLengthValidator extends Validator {
    public static final ViolationType TOO_LONG_SOURCE = new ViolationType("source code is too long (over 50 lines).");
    private static final LineCount MAX_SOURCE_LENGTH = new LineCount(50);

    @Override
    public void endVisit(CompilationUnit node){
        checkViolationOfClassDefinition(countLinesOf(node));
        super.endVisit(node);
    }

    private void checkViolationOfClassDefinition(LineCount lineNumber){
        if(lineNumber.isGreaterThan(MAX_SOURCE_LENGTH)){
            this.addViolation(new Violation(TOO_LONG_SOURCE, new LineCounts(lineNumber)));
        }
    }
}
