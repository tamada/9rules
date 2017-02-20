package com.github.ninerules;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.violations.Violation;

public class Reporter {
    private PrintWriter out;

    public Reporter() throws IOException{
        this(new PrintWriter(new OutputStreamWriter(System.out, "utf-8")));
    }

    public Reporter(PrintWriter out) throws IOException{
        this.out = out;
    }

    public void report(Results results){
        results.report(this);
        out.flush();
    }

    public void report(FileName name, List<Violation> list){
        out.println(name);
        list.stream()
        .forEach(out::println);
    }
}
