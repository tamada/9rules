package com.github.ninerules.rules.indentlevel;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.formatter.IndentManipulation;

import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class IndentLevelValidator extends Validator {
    public static final ViolationType INDENT_LEVEL = new ViolationType("Indentation level is too much");
    private static final int MAX_INDENT_SIZE = 6;

    @Override
    public boolean visit(MethodDeclaration node){
        checkViolation(node);
        return super.visit(node);
    }

    private void checkViolation(MethodDeclaration node){
        if(isViolated(node)){
            addViolation(new Violation(INDENT_LEVEL, new LineCounts(startLine(node))));
        }
    }

    private boolean isViolated(MethodDeclaration node){
        return indentedStringStream(node).mapToInt(line -> getIndentSize(line))
                .max()
                .orElse(0) >= MAX_INDENT_SIZE;
    }

    private int getIndentSize(String line){
        String trim = line.trim();
        char firstChar = trim.charAt(0);
        return line.indexOf(firstChar);
    }

    private Stream<String> indentedStringStream(MethodDeclaration node){
        String indentedCode = IndentManipulation.changeIndent(node.toString(), 0, 8, 2, "", "\r\n");
        String[] lines = indentedCode.split("\r\n");
        return Arrays.stream(lines);
    }
}
