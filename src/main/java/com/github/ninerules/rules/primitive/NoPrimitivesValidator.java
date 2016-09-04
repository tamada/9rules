package com.github.ninerules.rules.primitive;

import java.util.function.Predicate;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.FieldChecker;
import com.github.ninerules.rules.FieldCollectingValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

/**
 * First class collection violation checker.
 * 
 * @author Haruaki Tamada
 */
public class NoPrimitivesValidator extends FieldCollectingValidator<NullParameter>{
    public static final ViolationType NO_PRIMITIVES = new ViolationType("no primitives.");
    private FieldChecker checker = new FieldChecker();
    private Predicate<FieldDeclaration> predicate = (item) -> !checker.checkStatic(item);

    public NoPrimitivesValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void endVisit(CompilationUnit node) {
        checkViolation();
        super.endVisit(node);
    }

    private void checkViolation(){
        if(isViolated()){
            addViolation(new Violation(NO_PRIMITIVES, lineNumbers(predicate)));
        }
    }

    private boolean isViolated(){
        long fieldSize = computesFieldCount(predicate);
        long primitivesSize = computesFieldCount(predicate.and(
                item -> new PrimitiveChecker().check(item)));
        return fieldSize > 1 && primitivesSize > 0;
    }

    @Override
    public Parameter<NullParameter> parameter() {
        return NullParameter.parameter();
    }
}
