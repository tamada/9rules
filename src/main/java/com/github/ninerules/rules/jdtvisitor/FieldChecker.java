package com.github.ninerules.rules.jdtvisitor;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;

public class FieldChecker {
    private TypeChecker checker = new TypeChecker();

    public boolean checkCollection(FieldDeclaration field){
        Type fieldType = field.getType();
        return fieldType.isArrayType() || 
                checker.isCollection(fieldType);
    }

    @SuppressWarnings("unchecked")
    private List<Modifier> modifiers(FieldDeclaration node){
        return (List<Modifier>)node.modifiers();
    }

    public boolean checkStatic(FieldDeclaration node){
        return modifiers(node).stream()
                .filter(modifier -> modifier.isStatic())
                .collect(Collectors.counting()) == 1;
    }

    public boolean checkStaticAndFinal(FieldDeclaration node){
        return modifiers(node).stream()
                .filter(modifier -> isStaticOrFinal(modifier))
                .collect(Collectors.counting()) == 2;
    }

    private boolean isStaticOrFinal(Modifier modifier){
        return modifier.isFinal() 
                || modifier.isStatic();        
    }
}
