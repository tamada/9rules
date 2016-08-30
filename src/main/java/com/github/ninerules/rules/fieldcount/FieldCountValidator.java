package com.github.ninerules.rules.fieldcount;

import java.util.function.Predicate;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import com.github.ninerules.rules.FieldChecker;
import com.github.ninerules.rules.FieldCollectingValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class FieldCountValidator extends FieldCollectingValidator {
    public static final ViolationType FIELD_COUNT = new ViolationType("field count is more than 2");

    private FieldChecker checker = new FieldChecker();
    private Predicate<FieldDeclaration> predicate = (item) -> !checker.checkStaticAndFinal(item);

    @Override
    public void endVisit(CompilationUnit unit){
        checkViolation();
        super.endVisit(unit);        
    }

    private void checkViolation(){
        long count = computesFieldCount(item -> !checker.checkStatic(item));
        if(computesFieldCount(item -> !checker.checkStatic(item)) > 2){
            addViolation(new Violation(FIELD_COUNT, lineNumbers(predicate)));
        }
    }
}
