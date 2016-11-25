package com.github.ninerules.rules.fieldcount;

import java.util.function.Predicate;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.FieldCount;
import com.github.ninerules.rules.jdtvisitor.FieldChecker;
import com.github.ninerules.rules.jdtvisitor.FieldCollectingValidator;

public class FieldCountValidator extends FieldCollectingValidator {
    public static final Message FIELD_COUNT = new Message("field count is more than %s");

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
            addViolation(buildViolation(FIELD_COUNT, lineNumbers(predicate)));
        }
    }

    @Override
    public Class<FieldCount> parameterClass() {
        return FieldCount.class;
    }
}
