package com.github.ninerules.entities;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.results.Summary;

public class Context {
    private StrictLevel level = StrictLevel.STRICT;
    private boolean noSummary = false;

    public Context(StrictLevel level, boolean noSummaryFlag) {
        this.level = level;
        this.noSummary = noSummaryFlag;
    }

    public StrictLevel level() {
        return level;
    }

    public Results buildSummary(Results results) {
        if(noSummary)
            return results;
        return new Summary(results);
    }
}
