package com.github.ninerules;

import java.io.PrintWriter;
import java.util.List;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.results.Summary;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.utils.Pair;

public class Reporter {
    private PrintWriter out;

    public Reporter(PrintWriter out){
        this.out = out;
    }

    public void report(Summary summary) {
        summary.report(this);
        out.flush();
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

    public void summarize(Summary summary) {
        Pair<Long, Long> pair = summary.summarizeResults();
        pair.apply((left, right) -> summarize(left, right));
    }

    private void summarize(long fileCount, long indicationCount) {
        out.printf("9rules found %d violations in %d files.%n", indicationCount, fileCount);
    }
}
