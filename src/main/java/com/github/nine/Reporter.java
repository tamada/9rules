package com.github.nine;

import java.io.PrintWriter;

import com.github.nine.rules.Results;

public class Reporter {
    private PrintWriter out;

    public Reporter(){
        this(new PrintWriter(System.out));
    }

    public Reporter(PrintWriter out){
        this.out = out;
    }

    public void report(Results results){
        results.report(out);
        out.flush();
    }
}
