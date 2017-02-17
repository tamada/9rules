package com.github.ninerules.rules.indentlevel;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.formatter.IndentManipulation;

public class IndentManipulator {
    private MethodDeclaration node;

    public IndentManipulator(MethodDeclaration block) {
        this.node = block;
    }

    private Stream<String> buildStream(String line) {
        String[] lines = line.split("\r\n");
        return Arrays.stream(lines);
    }

    private String indentedString(Optional<MethodDeclaration> block) {
        return block.map(Object::toString)
                .orElse("");
    }

    public Stream<String> stream() {
        String string = indentedString(Optional.ofNullable(node));
        String indentedCode = IndentManipulation.changeIndent(string, 0, 8, 2, "", "\r\n");
        return buildStream(indentedCode);
    }
}