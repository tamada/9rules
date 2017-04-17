package com.github.ninerules;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.ninerules.cli.Argument;
import com.github.ninerules.cli.CommandLines;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.traverser.ExtensionFilter;
import com.github.ninerules.traverser.Traverser;

public class Main{
    public Main(String[] args, PrintWriter out){
        CommandLines commandline = new CommandLines(args);
        performUnlessHelp(commandline, out);
    }

    private void performUnlessHelp(CommandLines commandline, PrintWriter out){
        if(commandline.printHelpIfSpecified(out))
            return;
        new Reporter(out).report(perform(commandline));
    }

    public Results perform(CommandLines commandline){
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
        return stream.map(Argument::toPath)
                .flatMap(traverser::stream);
    }

    public static void main(String[] args) throws IOException {
        try(PrintWriter out = new PrintWriter(System.out)){
            new Main(args, out);
        }
    }
}
