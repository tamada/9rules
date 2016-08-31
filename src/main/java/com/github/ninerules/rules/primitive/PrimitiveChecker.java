package com.github.ninerules.rules.primitive;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Type;

public class PrimitiveChecker {
    private List<String> list = Arrays.asList("byte", "short", "int", "long", "float",
        "double", "boolean", "char", "(java.lang.)?String", "(java.util.)?Date");

    public boolean check(FieldDeclaration node){
        Type type = node.getType();
        return check(type.toString());
    }

    private boolean check(String type){
        return list.stream()
                .filter(item -> type.matches(item))
                .count() > 0;
    }    
}
