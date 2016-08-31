package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;

public class FieldChecker {
    private static final List<String> MATCH_STRING = new ArrayList<>();

    static{
        MATCH_STRING.add("(java.util.)?([A-Z][a-z]+)?List(<.*>)?");
        MATCH_STRING.add("(java.util.)?([A-Z][a-z]+)?Deque(<.*>)?");
        MATCH_STRING.add("(java.util.)?([A-Z][a-z]+)?Queue(<.*>)?");
        MATCH_STRING.add("(java.util.)?([A-Z][a-z]+)?Set(<.*>)?");
        MATCH_STRING.add("(java.util.)?([A-Z][a-z]+)?Map(<.*, *.*>)?");
    }
    
    public boolean checkCollection(FieldDeclaration field){
        Type fieldType = field.getType();
        String type = fieldType.toString();
        return fieldType.isArrayType() || 
                MATCH_STRING.stream()
                .anyMatch(item -> type.matches(item));
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
