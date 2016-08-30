package com.github.ninerules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.rules.Results;
import com.github.ninerules.rules.Rules;

public class NineRulesValidator {
    public Results validate(List<Path> list){
        return list.stream()
                .map(path -> parse(path))
                .map(unit -> validate(unit))
                .reduce((result1, result2) -> result1.append(result2))
                .orElse(Results.empty());
    }

    private Results validate(Target target){
        return new Rules().validate(target);
    }

    public Target parse(Path path){
        String source = readSource(path);
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(source.toCharArray());
        return new Target(path, (CompilationUnit)parser.createAST(new NullProgressMonitor()));
    }

    private String readSource(Path path){
        try {
            return Files.lines(path)
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
        }
        return "";
    }
}
