package com.github.ninerules.rules.firstclasscollection;

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
public class FirstClassCollectionValidator extends FieldCollectingValidator{
    public static final Message FCC = new Message("not first class collection.");
    private FieldChecker checker = new FieldChecker();
    private Predicate<FieldDeclaration> predicate = (item) -> !checker.checkStaticAndFinal(item);

    public FirstClassCollectionValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void endVisit(CompilationUnit node) {
        checkViolation();
        super.endVisit(node);
    }

    private void checkViolation(){
        if(isViolated()){
            addViolation(buildViolation(FCC, lineNumbers(predicate)));
        }
    }

    private long computesFieldCount(){
        Predicate<FieldDeclaration> predicate2 = (item) -> checker.checkCollection(item);
        return computesFieldCount(predicate.and(predicate2));
    }

    private boolean isViolated(){
        long fieldSize = computesFieldCount(predicate);
        long collectionSize = computesFieldCount();
        return fieldSize > 1 && collectionSize > 0;
    }
}
