package com.github.ninerules.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceLoader<T> {
    private List<String> list;

    private ServiceLoader(Stream<String> stream){
        list = stream.collect(Collectors.toList());
    }

    public Stream<Class<T>> stream(){
        return list.stream()
                .map(className -> stringToClass(className));
    }

    @SuppressWarnings("unchecked")
    private Class<T> stringToClass(String className){
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> ServiceLoader<T> load(Class<T> clazz){
        try {
            return load(clazz, clazz.getClassLoader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> ServiceLoader<T> load(Class<T> clazz, ClassLoader loader) throws IOException{
        String path = "META-INF/services/" + clazz.getName();
        URL url = loader.getResource(path);
        return ServiceLoader.<T>loadFromUrl(url);
    }

    private static <T> ServiceLoader<T> loadFromUrl(URL url) throws IOException{
        try(BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))){
            return new ServiceLoader<T>(in.lines());
        }
    }
}
