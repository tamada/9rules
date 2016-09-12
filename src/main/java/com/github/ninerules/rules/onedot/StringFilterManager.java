package com.github.ninerules.rules.onedot;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

class StringFilterManager {
    private Function<String, String> function;

    public StringFilterManager(){
        Map<String, String> stringFrom = readResource();
        function = buildFunction(stringFrom.entrySet());
    }

    private Function<String, String> buildFunction(Set<Entry<String, String>> entries){
        return functionStream(entries)
                .reduce((filter,  next) -> filter.compose(next))
                .orElse(Function.identity());
    }

    private Stream<Function<String, String>> functionStream(Set<Entry<String, String>> entries){
        return entries.stream()
                .map(entry -> new StringFilter(entry));
    }

    private Map<String, String> readResource(){
        PropertyReader reader = new PropertyReader(
                getClass().getResource("/resources/StringFilter.properties"));
        return reader.read();
    }

    public String filter(String line){
        return function.apply(line);
    }
}
