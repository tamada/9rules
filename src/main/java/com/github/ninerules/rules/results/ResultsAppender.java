package com.github.ninerules.rules.results;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.Violation;

public class ResultsAppender {
    private Results results;

    public ResultsAppender(Results results){
        this.results = results;
    }

    public Results append(Results results){
        results.entryStream()
        .forEach(item -> put(item));
        return this.results;
    }

    private void put(Map.Entry<FileName, List<Violation>> entry){
        FileName name = entry.getKey();
        List<Violation> list = entry.getValue();
        put(name, list);
    }

    private void put(FileName name, List<Violation> list){
        Map<FileName, List<Violation>> map = results.violations;
        List<Violation> origList = getMergedList(map, name, list);
        map.put(name,  origList);
    }

    private List<Violation> getMergedList(Map<FileName, List<Violation>> map, FileName name, List<Violation> list){
        List<Violation> origList = map.getOrDefault(name, new ArrayList<>());
        origList.addAll(list);
        return origList;
    }
}
