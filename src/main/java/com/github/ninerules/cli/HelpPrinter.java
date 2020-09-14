package com.github.ninerules.cli;

import static io.vavr.control.Try.withResources;

import io.vavr.control.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.stream.Stream;

public class HelpPrinter {
    private PrintWriter out;

    public HelpPrinter(PrintWriter out){
        this.out = out;
    }

    public boolean printIfSpecified(CommandLines cli){
        return printIfSpecified(cli.isShowHelp(), this::printHelp);
    }

    private boolean printIfSpecified(boolean flag, Runnable runnable){
        if(flag)
            runnable.run();
        return flag;
    }

    public void printHelp(){
        URL url = getClass().getResource("/resources/help.txt");
        Try.run(() -> openAndPrintThem(url));
    }

    private void openAndPrintThem(URL location) throws IOException{
        withResources(() -> new BufferedReader(new InputStreamReader(location.openStream(), "utf-8")))
            .of(in -> printLines(in.lines()));
    }

    private boolean printLines(Stream<String> stream){
        stream.forEach(out::println);
        return true;
    }
}
