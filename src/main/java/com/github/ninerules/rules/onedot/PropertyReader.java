package com.github.ninerules.rules.onedot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.github.ninerules.utils.ExceptionHandler;

public class PropertyReader {
    private URL location;

    public PropertyReader(URL location){
        this.location = location;
    }

    public Map<String, String> read(){
        Map<String, String> map = new HashMap<>();
        return ExceptionHandler.perform(map, map, this::readProperty);
    }

    private Map<String, String> readProperty(Map<String, String> map) throws IOException{
        openAndReadProperty(map);
        return map;
    }

    private void openAndReadProperty(Map<String, String> map) throws IOException{
        try(BufferedReader in = new BufferedReader(new InputStreamReader(location.openStream(), "utf-8"))){
            read(map, in.lines());
        }
    }

    private void read(Map<String, String> map, Stream<String> stream) throws IOException{
        stream.forEach(line -> putSplittedItem(map, line));
    }

    private void putSplittedItem(Map<String, String> map, String item){
        int index = item.indexOf('=');
        String key = item.substring(0, index);
        map.put(key, parseLineToGetValue(item, index));
    }

    private String parseLineToGetValue(String line, int index){
        return line.substring(index + 1);
    }
}
