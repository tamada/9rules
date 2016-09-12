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
        int count = 0;
        while(matcher.find()) count++;
        return count;
    }
}
