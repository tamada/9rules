package com.github.ninerules;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.ninerules.rules.results.Results;
import com.github.ninerules.traverser.ExtensionFilter;
import com.github.ninerules.traverser.Traverser;

/**
 * 
 * @author Haruaki Tamada
 */
public class Main{
    public Main(String[] args){
        NineRulesValidator checker = new NineRulesValidator();
        Results results = checker.validate(listupTargets(args));
        new Reporter().report(results);
    }

    private List<Path> listupTargets(String[] args){
        Traverser traverser = new Traverser(new ExtensionFilter(".java"));
        return listupTargets(args, traverser);
    }

    private List<Path> listupTargets(String[] args, Traverser traverser){
        return Arrays.stream(args)
                .map(dir -> Paths.get(dir))
                .flatMap(dir -> traverser.stream(dir))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
