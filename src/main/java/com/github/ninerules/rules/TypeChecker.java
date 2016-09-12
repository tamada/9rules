package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Type;

public class TypeChecker {
    private static final List<String> COLLECTIONS = new ArrayList<>();

    static{
        COLLECTIONS.add("(java.util.)?([A-Z][a-z]+)?List(<.*>)?");
        COLLECTIONS.add("(java.util.)?([A-Z][a-z]+)?Deque(<.*>)?");
        COLLECTIONS.add("(java.util.)?([A-Z][a-z]+)?Queue(<.*>)?");
        COLLECTIONS.add("(java.util.)?([A-Z][a-z]+)?Set(<.*>)?");
        COLLECTIONS.add("(java.util.)?([A-Z][a-z]+)?Map(<.*, *.*>)?");
    }

    public boolean isCollection(Type fieldType){
        final String type = fieldType.toString();
        return COLLECTIONS.stream()
                .anyMatch(item -> type.matches(item));
    }
}
