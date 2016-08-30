package com.github.ninerules.rules;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.ninerules.entities.FileName;

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

    public Results append(Results results){
        results.violations
        .entrySet()
        .stream()
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
        violations.entrySet()
        .stream()
        .forEach(item -> report(out, item.getKey(), item.getValue()));
    }

    private void report(PrintWriter out, FileName name, List<Violation> list){
        out.println(name);
        list.stream().forEach(item -> out.println(item));
    }
}