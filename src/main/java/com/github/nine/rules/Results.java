package com.github.nine.rules;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.nine.entities.FileName;

public class Results {
    private Map<FileName, List<Violation>> violations = new HashMap<>();

    public Results(FileName fileName, List<Violation> list){
        violations.put(fileName, list);
    }

    private Results(){
    }

    public static final Results empty(){
        return new Results();
    }

    public Results append(Results results){
        results.violations.entrySet().stream()
        .forEach(item -> put(item.getKey(), item.getValue()));
        return this;
    }

    private void put(FileName name, List<Violation> list){
        List<Violation> origList = violations.get(name);
        if(origList == null){
            origList = new ArrayList<>();
            violations.put(name,  origList);
        }
        origList.addAll(list);
    }

    public void report(PrintWriter out){
        violations.entrySet().stream()
        .forEach(item -> report(out, item.getKey(), item.getValue()));
    }

    private void report(PrintWriter out, FileName name, List<Violation> list){
        out.println(name);
        list.stream().forEach(item -> out.println(item));
    }
}
