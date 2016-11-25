package com.github.ninerules.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Stream;

public class HelpPrinter {
    public boolean printIfSpecified(CommandLines cli){
        if(!cli.isShowHelp()) return false;
        printHelp();
        return true;
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

    private void printLines(Stream<String> stream) throws IOException{
        stream.forEach(line -> System.out.println(line));
    }
}
