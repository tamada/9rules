package com.github.ninerules.rules.indentlevel;

import java.util.Arrays;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.formatter.IndentManipulation;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.parameters.IndentLevel;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.JdtValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class IndentLevelValidator extends JdtValidator<IndentLevel>{
    public static final ViolationType INDENT_LEVEL = new ViolationType("Indentation level is too much");

    public IndentLevelValidator(StrictLevel level) {
        super(level);
    }

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
        return indentedStringStream(node)
                .map(line -> getIndentSize(line))
                .distinct()
                .max((indent1, indent2) -> indent1.compareTo(indent2))
                .orElse(new IndentLevel(0))
                .isGreaterThan(parameter());
    }

    private IndentLevel getIndentSize(String line){
        String trim = line.trim();
        char firstChar = trim.charAt(0);
        return new IndentLevel(line.indexOf(firstChar) - 2);
    }

    private Stream<String> indentedStringStream(MethodDeclaration node){
        String string = node.toString();
        String indentedCode = IndentManipulation.changeIndent(string, 0, 8, 2, "", "\r\n");
        String[] lines = indentedCode.split("\r\n");
        return Arrays.stream(lines);
    }

    @Override
    public IndentLevel parameter() {
        return Parameters.parameter(IndentLevel.class, level());
    }
}
