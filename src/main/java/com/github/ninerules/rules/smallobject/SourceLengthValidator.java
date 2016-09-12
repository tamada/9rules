package com.github.ninerules.rules.smallobject;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.SourceLength;
import com.github.ninerules.rules.JdtValidator;

public class SourceLengthValidator extends JdtValidator {
    public static final Message TOO_LONG_SOURCE = new Message("source code is too long (over %s lines).");

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
            this.addViolation(buildViolation(TOO_LONG_SOURCE, new LineCounts(lineNumber)));
        }
    }

    private LineCount limitLength(){
        return ((SourceLength)parameter()).convertToLineCount();
    }

    @Override
    public Class<SourceLength> parameterClass() {
        return SourceLength.class;
    }
}
