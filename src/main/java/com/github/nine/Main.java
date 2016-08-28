package com.github.nine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.nine.traverser.Traverser;

/**
 * 
 * @author Haruaki Tamada
 */
public class Main{
    public Main(String[] args){
        List<Path> list = listupTargets(args);

        SopChecker checker = new SopChecker();
        checker.check(list);
    }

    private List<Path> listupTargets(String[] args){
        final Traverser traverser = new Traverser((name, attributes) -> name.toString().endsWith(".java"));
        List<Path> list = Arrays.stream(args)
                .map(dir -> Paths.get(dir))
                .flatMap(dir -> traverser.stream(dir))
                .collect(Collectors.toList());

        return list;
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
