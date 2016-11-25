package com.github.ninerules.rules.accessor;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.Message;
import com.github.ninerules.rules.jdtvisitor.JdtValidator;

public class NoAccessorValidator extends JdtValidator{
    public static final Message SETTER = new Message("setter method found");
    public static final Message GETTER = new Message("getter method found");

    private AccessorChecker checker = new AccessorChecker();

    public NoAccessorValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        checkViolation(node);
        return super.visit(node);
    }

    private String getMethodName(MethodDeclaration node){
        SimpleName simpleName = node.getName();
        return simpleName.getIdentifier();
    }

    private void checkViolation(MethodDeclaration node){
        if(checker.isPublicMethod(node)){
            checkViolation(getMethodName(node), new LineCounts(startLine(node)));
        }
    }

    private void checkViolation(String methodName, LineCounts lineNumbers){
        checkViolation("get[A-Z][a-zA-Z]*$", GETTER, methodName, lineNumbers);
        checkViolation("set[A-Z][a-zA-Z]*$", SETTER, methodName, lineNumbers);
    }

    private void checkViolation(String pattern, Message message, String methodName, LineCounts lineNumbers){
        if(methodName.matches(pattern)){
            addViolation(buildViolation(message, lineNumbers));
        }
    }
}