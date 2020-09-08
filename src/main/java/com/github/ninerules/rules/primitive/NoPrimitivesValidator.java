package com.github.ninerules.rules.primitive;

import java.util.function.Predicate;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.Message;
import com.github.ninerules.rules.jdtvisitor.FieldChecker;
import com.github.ninerules.rules.jdtvisitor.FieldCollectingValidator;

/**
 * First class collection violation checker.
 * 
 * @author Haruaki Tamada
 */
public class NoPrimitivesValidator extends FieldCollectingValidator{
    public static final Message NO_PRIMITIVES = new Message("no primitives.");

    private FieldChecker checker = new FieldChecker();
    private Predicate<FieldDeclaration> predicate = item -> !checker.checkStatic(item);

    public NoPrimitivesValidator() {
        this(StrictLevel.STRICT);
    }

    public NoPrimitivesValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void endVisit(CompilationUnit node) {
        checkViolation();
        super.endVisit(node);
    }

    private void checkViolation(){
        if(isViolated())
            addViolation(buildViolation(NO_PRIMITIVES, lineNumbers(predicate)));
    }

    private Predicate<FieldDeclaration> createPredicate(){
        return predicate.and(PrimitiveChecker::check);
    }

    private boolean isViolated(){
        long fieldSize = computesFieldCount(predicate);
        long primitivesSize = computesFieldCount(createPredicate());
        return fieldSize > 1 && primitivesSize > 0;
    }
}
