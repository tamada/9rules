package com.github.ninerules.rules.onedot;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MatchingCounter implements Function<String, Integer> {
    private Pattern pattern;

    public MatchingCounter(Pattern pattern){
        this.pattern = pattern;
    }

    @Override
    public Integer apply(String string) {
        return count(pattern.matcher(string));
    }

    private int count(Matcher matcher){
        return count(matcher, new Counter());
    }

    private int count(Matcher matcher, Counter counter){
        while(matcher.find())
            counter.increment();
        return counter.value();
    }
}

