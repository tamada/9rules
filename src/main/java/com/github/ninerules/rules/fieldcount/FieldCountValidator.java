package com.github.ninerules.rules.fieldcount;

import java.util.function.Predicate;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.parameters.FieldCount;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.FieldChecker;
import com.github.ninerules.rules.FieldCollectingValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class FieldCountValidator extends FieldCollectingValidator<FieldCount> {
    public static final ViolationType FIELD_COUNT = new ViolationType("field count is more than 2");

    private FieldChecker checker = new FieldChecker();
    private Predicate<FieldDeclaration> predicate = (item) -> !checker.checkStaticAndFinal(item);

    public FieldCountValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void endVisit(CompilationUnit unit){
        checkViolation();
        super.endVisit(unit);        
    }

    private void checkViolation(){
        if(computesFieldCount(item -> !checker.checkStatic(item)) > 2){
            addViolation(new Violation(FIELD_COUNT, lineNumbers(predicate)));
        }
    }

    @Override
    public FieldCount parameter() {
        return Parameters.parameter(
                FieldCount.class, level());
    }
}
