package com.github.ninerules.rules.onedot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PropertyReader {
    private URL location;

    public PropertyReader(URL location){
        this.location = location;
    }

    public Map<String, String> read(){
        Map<String, String> map = new HashMap<>();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(location.openStream()))){
            read(map, in.lines());
        } catch(IOException e){
        }
        return map;
    }

    private void read(Map<String, String> map, Stream<String> stream) throws IOException{
        stream.forEach(line -> {
            String[] pair = line.split("=");
            map.put(pair[0], getPair(pair, 1));
        });
    }

    private String getPair(String[] pair, int index){
        if(index < pair.length){
            return pair[index];
        }
        return "";
    }

}
