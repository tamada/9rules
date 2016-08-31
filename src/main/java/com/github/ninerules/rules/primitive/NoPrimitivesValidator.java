package com.github.ninerules.rules.primitive;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Type;

import com.github.ninerules.rules.FieldChecker;
import com.github.ninerules.rules.FieldCollectingValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

/**
 * First class collection violation checker.
 * 
 * @author Haruaki Tamada
 */
public class NoPrimitivesValidator extends FieldCollectingValidator{
    public static final ViolationType NO_PRIMITIVES = new ViolationType("no primitives.");
    private FieldChecker checker = new FieldChecker();
    private Predicate<FieldDeclaration> predicate = (item) -> !checker.checkStatic(item);

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
        long primitivesSize = computesFieldCount(predicate.and(item -> checkPrimitives(item)));
        return fieldSize > 1 && primitivesSize > 0;
    }

    private boolean checkPrimitives(FieldDeclaration node){
        Type type = node.getType();
        return checkPrimitives(type.toString());
    }

    private boolean checkPrimitives(String type){
        List<String> list = Arrays.asList("byte", "short", "int", "long", "float",
                "double", "boolean", "char", "(java.lang.)?String", "(java.util.)?Date");
        return list.stream()
                .filter(item -> type.matches(item))
                .count() > 0;
    }    
}
