package com.github.ninerules;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.ninerules.entities.Context;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.github.ninerules.rules.Rules;
import com.github.ninerules.rules.results.Results;

public class NineRulesValidator {
    private static final int PARSER_LEVEL = AST.JLS12;
    private Context context;

    public NineRulesValidator(Context context){
        this.context = context;
    }

    public Results validate(List<Path> list){
        return context.buildSummary(
                validateOf(list.stream())
                .orElse(Results.empty()));
    }

    private Optional<Results> validateOf(Stream<Path> stream){
        return stream.map(this::validate)
                .reduce(Results::append);
    }

    private Results validate(Path path){
        Target target = parse(path);
        return new Rules(context.level())
                .validate(target);
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
