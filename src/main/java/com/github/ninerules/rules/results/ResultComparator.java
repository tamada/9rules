package com.github.ninerules.rules.results;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.Violation;

public class ResultComparator implements Comparator<Map.Entry<FileName, List<Violation>>>{
    @Override
    public int compare(Entry<FileName, List<Violation>> entry1,
            Entry<FileName, List<Violation>> entry2) {
        FileName name1 = entry1.getKey();
        FileName name2 = entry2.getKey();
        return name1.compareTo(name2);
    }

}
