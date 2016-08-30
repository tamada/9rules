package com.github.ninerules.rules;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;

public class FieldChecker {
    public boolean checkCollection(FieldDeclaration field){
        Type fieldType = field.getType();
        String type = fieldType.toString();
        
        return fieldType.isArrayType()
                || type.matches("(java.util.)?([A-Z][a-z]+)?List(<.*>)?")
                || type.matches("(java.util.)?([A-Z][a-z]+)?Deque(<.*>)?")
                || type.matches("(java.util.)?([A-Z][a-z]+)?Queue(<.*>)?")
                || type.matches("(java.util.)?([A-Z][a-z]+)?Set(<.*>)?")
                || type.matches("(java.util.)?([A-Z][a-z]+)?Map(<.*, *.*>)?");
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
        return modifier.isFinal() || modifier.isStatic();        
    }
}
