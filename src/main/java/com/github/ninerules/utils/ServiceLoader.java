package com.github.ninerules.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
        }
        return null;
    }

    public static <T> ServiceLoader<T> load(Class<T> clazz){
        try {
            return load(clazz, clazz.getClassLoader());
        } catch (IOException e) {
        }
        return new ServiceLoader<T>(Stream.empty());
    }

    public static <T> ServiceLoader<T> load(Class<T> clazz, ClassLoader loader) throws IOException{
        String path = "META-INF/services/" + clazz.getName();
        Optional<URL> url = Optional.ofNullable(loader.getResource(path));
        return ServiceLoader.<T>loadFromUrl(url.orElseThrow(() -> new IOException()));
    }

    private static <T> ServiceLoader<T> loadFromUrl(URL url) throws IOException{
        try(BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))){
            return new ServiceLoader<T>(in.lines());
        }
    }
}
