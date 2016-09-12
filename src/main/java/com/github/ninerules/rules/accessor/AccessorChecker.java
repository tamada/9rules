package com.github.ninerules.rules.accessor;

import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

class AccessorChecker {
    public boolean isPublicMethod(MethodDeclaration node){
        return stream(node)
                .filter(modifier -> ((Modifier)modifier).isPublic())
                .count() == 1;
    }

    @SuppressWarnings("unchecked")
    private Stream<Object> stream(MethodDeclaration node){
        return node.modifiers()
                .stream()
                .filter(modifier -> modifier instanceof Modifier);
    }
}
