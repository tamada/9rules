package com.github.ninerules.cli;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.github.ninerules.StrictLevel;

public class CommandLines {
    private Options options;
    private Arguments arguments;

    public CommandLines(String[] args){
        applyFields(args, string -> string.startsWith("-"));
    }

    public boolean printHelpIfSpecified(PrintWriter out){
        HelpPrinter printer = new HelpPrinter(out);
        return printer.printIfSpecified(this);
    }

    public StrictLevel level(){
        return options.level();
    }

    private void applyFields(String[] args, Predicate<String> predicate){
        options = new Options(buildFilter(args, predicate).map(Option::new));
        arguments = new Arguments(buildFilter(args, predicate.negate())
                .map(Argument::new));
    }

    private Stream<String> buildFilter(String[] args, Predicate<String> predicate){
        return Arrays.stream(args)
                .filter(predicate);
    }

    public boolean hasOption(Option option){
        return options.isSpecified(option);
    }

    public Stream<Argument> arguments(){
        return arguments.stream();
    }

    public boolean isShowHelp(){
        return hasOption(Option.HELP_OPTION) 
            || arguments.isEmpty();
    }
}
