package com.github.ninerules.rules.smallobject;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.parameters.SourceLength;
import com.github.ninerules.rules.JdtValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class SourceLengthValidator extends JdtValidator<SourceLength> {
    public static final ViolationType TOO_LONG_SOURCE = new ViolationType("source code is too long (over 50 lines).");

    public SourceLengthValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void endVisit(CompilationUnit node){
        checkViolationOfClassDefinition(countLinesOf(node));
        super.endVisit(node);
    }

    private void checkViolationOfClassDefinition(LineCount lineNumber){
        if(lineNumber.isGreaterThan(limitLength())){
            this.addViolation(new Violation(TOO_LONG_SOURCE, new LineCounts(lineNumber)));
        }
    }

    private LineCount limitLength(){
        return parameter().convertToLineCount();
    }

    @Override
    public SourceLength parameter() {
        return Parameters.parameter(
                SourceLength.class, level());
    }
}
