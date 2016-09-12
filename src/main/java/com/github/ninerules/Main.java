package com.github.ninerules;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.ninerules.cli.Argument;
import com.github.ninerules.cli.CommandLines;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.traverser.ExtensionFilter;
import com.github.ninerules.traverser.Traverser;

/**
 * 
 * @author Haruaki Tamada
 */
public class Main{
    public Main(String[] args){
        CommandLines commandline = CommandLines.parse(args);
        Results results = perform(commandline);
        new Reporter().report(results);
    }

    private Results perform(CommandLines commandline){
        NineRulesValidator checker = new NineRulesValidator(commandline.level());
        List<Path> list = listupTargets(commandline.arguments());
        return checker.validate(list);
    }

    public List<Path> listupTargets(Stream<Argument> arguments){
        Traverser traverser = new Traverser(new ExtensionFilter(".java"));
        return listupTargets(arguments, traverser)
                .collect(Collectors.toList());
    }

    private Stream<Path> listupTargets(Stream<Argument> stream, Traverser traverser){
        return stream
                .map(argument -> argument.toPath())
                .flatMap(dir -> traverser.stream(dir));
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
