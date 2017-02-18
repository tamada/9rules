package com.github.ninerules.rules.primitive;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Type;

class PrimitiveChecker {
    private static List<String> list = Arrays.asList("byte", "short", "int", "long", "float",
        "double", "boolean", "char", "(java.lang.)?String", "(java.util.)?Date");

    private PrimitiveChecker(){
    }
    
    public static boolean check(FieldDeclaration node){
        Type type = node.getType();
        return check(type.toString());
    }

    static boolean check(String type){
        return list.stream()
                .filter(type::matches)
                .count() > 0;
    }    
}
