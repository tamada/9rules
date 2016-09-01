package com.github.ninerules.rules.onedot;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class StringFilter {
    private Map<String, String> stringFrom = new HashMap<>();

    public StringFilter(){
        PropertyReader reader = new PropertyReader(
                getClass().getResource("/resources/StringFilter.properties"));
        stringFrom = reader.read();
    }

    public String filter(String line){
        for(Entry<String, String> entry: stringFrom.entrySet()){
            line = replace(line, entry);
        }
        return line.trim();
    }

    private String replace(String line, Entry<String, String> entry){
        String from = entry.getKey();
        String to = entry.getValue();
        return line.replaceAll(from, to);
    }        
}
