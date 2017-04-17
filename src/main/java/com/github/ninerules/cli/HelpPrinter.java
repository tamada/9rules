package com.github.ninerules.cli;

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
        try{
            openAndPrintThem(getClass().getResource("/resources/help.txt"));
        } catch(IOException e){ throw new InternalError(e); }
    }

    private void openAndPrintThem(URL location) throws IOException{
        try(BufferedReader in = new BufferedReader(new InputStreamReader(location.openStream(), "utf-8"))){
            printLines(in.lines());
        }
    }

    private void printLines(Stream<String> stream){
        stream.forEach(out::println);
    }
}
