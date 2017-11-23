package com.github.ninerules;

import java.io.PrintWriter;
import java.util.List;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.Violation;
import com.github.ninerules.rules.results.Results;

public class Reporter {
    private PrintWriter out;

    public Reporter(PrintWriter out){
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
