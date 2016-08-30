package com.github.ninerules.rules.noaccessor;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class NoAccessorValidator extends Validator{
    public static final ViolationType SETTER = new ViolationType("setter method found");
    public static final ViolationType GETTER = new ViolationType("getter method found");

    @Override
    public boolean visit(MethodDeclaration node) {
        String name = getMethodName(node);
        LineCount lineNumber = startLine(node);
        check(name, new LineCounts(lineNumber));
        return super.visit(node);
    }

    private String getMethodName(MethodDeclaration node){
        SimpleName simpleName = node.getName();
        return simpleName.getIdentifier();
    }

    private void check(String methodName, LineCounts lineNumbers){
        if(methodName.matches("get[A-Z][a-zA-Z]*$")){
            addViolation(new Violation(GETTER, lineNumbers));
        }
        else if(methodName.matches("set[A-Z][a-zA-Z]*")){
            addViolation(new Violation(SETTER, lineNumbers));
        }
    }
}
