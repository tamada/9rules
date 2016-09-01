package com.github.ninerules;

import java.nio.file.Path;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.rules.Rules;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.results.ResultsAppender;

public class NineRulesValidator {
    private static final int PARSER_LEVEL = AST.JLS8;

    public Results validate(List<Path> list){
        return list.stream()
                .map(path -> validate(path))
                .reduce((result1, result2) -> new ResultsAppender(result1).append(result2))
                .orElse(Results.empty());
    }

    private Results validate(Path path){
        Target target = parse(path);
        return new Rules().validate(target);
    }

    public Target parse(Path path){
        ASTParser parser = ASTParser.newParser(PARSER_LEVEL);
        parser.setSource(source(path));
        return new Target(path, (CompilationUnit)parser.createAST(new NullProgressMonitor()));
    }

    private char[] source(Path path){
        SourceParser parser = new SourceParser(path);
        String source = parser.parse();
        return source.toCharArray();
    }
}
