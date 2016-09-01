package com.github.ninerules.rules.accessor;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;

import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.rules.JdtValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class NoAccessorValidator extends JdtValidator{
    public static final ViolationType SETTER = new ViolationType("setter method found");
    public static final ViolationType GETTER = new ViolationType("getter method found");

    @Override
    public boolean visit(MethodDeclaration node) {
        if(isPublicMethod(node)){
            checkViolation(getMethodName(node), new LineCounts(startLine(node)));
        }
        return super.visit(node);
    }

    @SuppressWarnings("unchecked")
    private boolean isPublicMethod(MethodDeclaration node){
        return node.modifiers()
                .stream()
                .filter(modifier -> modifier instanceof Modifier)
                .filter(modifier -> ((Modifier)modifier).isPublic())
                .count() == 1;
    }

    private String getMethodName(MethodDeclaration node){
        SimpleName simpleName = node.getName();
        return simpleName.getIdentifier();
    }

    private void checkViolation(String methodName, LineCounts lineNumbers){
        if(methodName.matches("get[A-Z][a-zA-Z]*$")){
            addViolation(new Violation(GETTER, lineNumbers));
        }
        else if(methodName.matches("set[A-Z][a-zA-Z]*")){
            addViolation(new Violation(SETTER, lineNumbers));
        }
    }
}