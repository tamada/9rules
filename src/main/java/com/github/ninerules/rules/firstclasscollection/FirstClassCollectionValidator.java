package com.github.ninerules.rules.firstclasscollection;

import java.util.function.Predicate;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import com.github.ninerules.rules.FieldChecker;
import com.github.ninerules.rules.FieldCollectingValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

/**
 * First class collection violation checker.
 * 
 * @author Haruaki Tamada
 */
public class FirstClassCollectionValidator extends FieldCollectingValidator{
    public static final ViolationType FCC = new ViolationType("not first class collection.");
    private FieldChecker checker = new FieldChecker();
    private Predicate<FieldDeclaration> predicate = (item) -> !checker.checkStaticAndFinal(item);

    @Override
    public void endVisit(CompilationUnit node) {
        checkViolation();
        super.endVisit(node);
    }

    private void checkViolation(){
        if(isViolated()){
            addViolation(new Violation(FCC, lineNumbers(predicate)));
        }
    }

    private boolean isViolated(){
        long fieldSize = computesFieldCount(predicate);
        long collectionSize = computesFieldCount(predicate.and(item -> checker.checkCollection(item)));
        return fieldSize > 1 && collectionSize > 0;
    }

}
