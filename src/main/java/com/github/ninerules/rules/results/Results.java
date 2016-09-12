package com.github.ninerules.rules.results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.github.ninerules.Reporter;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.Violation;

public class Results {
    Map<FileName, List<Violation>> violations = new HashMap<>();

    public Results(FileName fileName, List<Violation> list){
        violations.put(fileName, list);
    }

    private Results(){
    }

    public static final Results empty(){
        return new Results();
    }

    public boolean contains(FileName fileName, Violation violation){
        List<Violation> list = violations.getOrDefault(fileName, new ArrayList<>());
        return list.contains(violation);
    }

    public void report(Reporter reporter){
        entryStream()
        .sorted(new ResultComparator())
        .forEach(item -> report(reporter, item));
    }

    public void report(Reporter reporter, Map.Entry<FileName, List<Violation>> entry){
        FileName name = entry.getKey();
        List<Violation> violations = entry.getValue();
        reporter.report(name, violations);
    }

    Stream<Map.Entry<FileName, List<Violation>>> entryStream(){
        return violations.entrySet()
                .stream();
    }

    public Results append(Results results){
        return new ResultsAppender(this).append(results);
    }
}