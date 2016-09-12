package com.github.ninerules.rules.indentlevel;

import java.util.Optional;
import java.util.stream.Stream;

import com.github.ninerules.parameters.IndentLevel;
import com.github.ninerules.utils.StringUtils;

public class IndentLevelStream {
    private IndentLevel buildIndentLevel(String line, String trim) {
        char firstChar = trim.charAt(0);
        int indent = line.indexOf(firstChar);
        return new IndentLevel((indent == 0? 0: indent - 2) / 2);
    }

    private IndentLevel buildIndentLevel(String line) {
        return buildIndentLevel(line, line.trim());
    }

    private IndentLevel parseIndentLevel(String line) {
        Optional<String> indentLevel = StringUtils.optional(line);
        return indentLevel.map(string -> buildIndentLevel(string))
                .orElse(new IndentLevel(0));
    }

    public Stream<IndentLevel> stream(IndentManipulator manipulator) {
        return manipulator.stream()
                .map(item -> parseIndentLevel(item))
                .distinct();
    }
}
