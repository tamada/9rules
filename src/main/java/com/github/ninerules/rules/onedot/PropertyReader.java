package com.github.ninerules.rules.onedot;

import static io.vavr.control.Try.withResources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.github.ninerules.utils.Pair;
import io.vavr.control.Try;

public class PropertyReader {
    private URL location;

    public PropertyReader(URL location){
        this.location = location;
    }

    public Map<String, String> read(){
        Map<String, String> map = new HashMap<>();
        return Try.of(() -> readProperty(map))
                .getOrElse(map);
    }

    private Map<String, String> readProperty(Map<String, String> map) throws IOException{
        openAndReadProperty(map);
        return map;
    }

    private void openAndReadProperty(Map<String, String> map) throws IOException{
        withResources(() -> new BufferedReader(new InputStreamReader(location.openStream(), "utf-8")))
                .of(in -> readImpl(map, in.lines()))
                .getOrElseThrow(e -> new IOException(e));
    }

    private boolean readImpl(Map<String, String> map, Stream<String> stream){
        stream.map(line -> splitItem(line))
                .forEach(pair -> pair.apply(map::put));
        return true;
    }

    private Pair<String, String> splitItem(String item) {
        int index = item.indexOf('=');
        return new Pair<>(item.substring(0, index),
                item.substring(index + 1));
    }
}
