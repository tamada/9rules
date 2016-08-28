package com.github.nine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.nine.rules.Results;
import com.github.nine.rules.Rules;

public class SopChecker {
    public void check(List<Path> list){
        Results results = list.stream()
                .map(path -> parse(path))
                .map(unit -> check(unit))
                .reduce((result1, result2) -> result1.append(result2))
                .orElse(Results.empty());
        new Reporter().report(results);
    }

    private Results check(Target target){
        Rules rules = new Rules();
        return rules.check(target);
    }

    private Target parse(Path path){
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
