package com.github.ninerules;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.rules.Rules;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.results.ResultsAppender;

public class NineRulesValidator {
    private static final int PARSER_LEVEL = AST.JLS8;
    private StrictLevel level = StrictLevel.STRICT;

    public NineRulesValidator(StrictLevel level){
        this.level = level;
    }

    public Results validate(List<Path> list){
        return validateOf(list.stream(), level)
                .orElse(Results.empty());
    }

    private Optional<Results> validateOf(Stream<Path> stream, StrictLevel level){
        return stream.map(path -> validate(path, level))
                .reduce((result1, result2) -> new ResultsAppender(result1).append(result2));
    }

    private Results validate(Path path, StrictLevel level){
        Target target = parse(path);
        return new Rules(level).validate(target);
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
