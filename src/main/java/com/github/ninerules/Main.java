package com.github.ninerules;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        NineRulesValidator checker = new NineRulesValidator();
        List<Path> list = listupTargets(new String[0]);
        return checker.validate(list);
    }

    public List<Path> listupTargets(String[] args){
        Traverser traverser = new Traverser(new ExtensionFilter(".java"));
        return listup(args, traverser);
    }

    public List<Path> listup(String[] args, Traverser traverser){
        return listupTargets(args, traverser)
                .collect(Collectors.toList());
    }

    private Stream<Path> listupTargets(String[] args, Traverser traverser){
        return Arrays.stream(args)
                .map(dir -> Paths.get(dir))
                .flatMap(dir -> traverser.stream(dir));
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
