package com.github.ninerules.rules.onedot;

import java.util.Map.Entry;
import java.util.function.Function;

class StringFilter implements Function<String, String>{
    private Entry<String, String> entry;

    public StringFilter(Entry<String, String> entry){
        this.entry = entry;
    }

    @Override
    public String apply(String string) {
        String key = entry.getKey();
        String value = entry.getValue();
        return string.replaceAll(key, value);
    }
}
