package com.github.ninerules.rules.results;

import com.github.ninerules.Reporter;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.utils.Pair;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class Summary extends Results {
    private Results results;

    public Summary(Results results) {
        this.results = results;
    }

    public void report(Reporter reporter) {
        reporter.report(results);
        reporter.summarize(this);
    }

    public boolean contains(FileName fileName, Violation violation){
        return results.contains(fileName, violation);
    }

    public Pair<Long, Long> summarizeResults() {
        return Pair.of(count(results), countIndication(results));
    }

    private long countIndication(Results results) {
        return filterMap(results, entry -> entry.getValue())
                .mapToInt(list -> list.size())
                .sum();
    }

    private long count(Results results) {
        return filterMap(results, v -> v).count();
    }

    private <T> Stream<T> filterMap(Results results, Function<Map.Entry<FileName, List<Violation>>, T> mapper) {
        return results.entryStream()
                .filter(entry -> isRemain(entry.getValue()))
                .map(mapper);
    }

    private boolean isRemain(List<Violation> list) {
        return list.size() > 0;
    }
}
