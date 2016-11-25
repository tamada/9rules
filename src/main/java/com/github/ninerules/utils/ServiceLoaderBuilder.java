package com.github.ninerules.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

public class ServiceLoaderBuilder<T> {
    public ServiceLoader<T> load(Class<T> clazz){
        return ExceptionHandler.perform(clazz, 
                new ServiceLoader<T>(Stream.empty()),
                item -> load(item, item.getClassLoader()));
    }

    public ServiceLoader<T> load(Class<T> clazz, ClassLoader loader) throws IOException{
        String path = "META-INF/services/" + clazz.getName();
        Optional<URL> url = loadPath(path, loader);
        return loadFromUrl(url.orElseThrow(() -> new IOException()));
    }

    private ServiceLoader<T> loadFromUrl(URL url) throws IOException{
        try(BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"))){
            return new ServiceLoader<T>(in.lines());
        }
    }

    private Optional<URL> loadPath(String path, ClassLoader loader){
        URL url = loader.getResource(path);
        return Optional.ofNullable(url);
    }
}
