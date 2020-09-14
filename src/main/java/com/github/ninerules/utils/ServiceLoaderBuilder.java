package com.github.ninerules.utils;

import static io.vavr.control.Try.withResources;

import io.vavr.control.Try;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

public class ServiceLoaderBuilder<T> {
    public ServiceLoader<T> load(Class<T> clazz){
        return load(clazz, clazz.getClassLoader());
    }

    public ServiceLoader<T> load(Class<T> clazz, ClassLoader loader) {
        return loadImpl(clazz, loader)
                .flatMap(tries -> tries.toJavaOptional())
                .orElseGet(() -> new ServiceLoader<>(Stream.empty()));
    }

    public Optional<Try<ServiceLoader<T>>> loadImpl(Class<T> clazz, ClassLoader loader) {
        String path = "META-INF/services/" + clazz.getName();
        return loadPath(path, loader)
                .map(url -> loadFromUrl(url));
    }

    private Try<ServiceLoader<T>> loadFromUrl(URL url) {
        return withResources(() -> new BufferedReader(new InputStreamReader(url.openStream(), "utf-8")))
                .of(in -> new ServiceLoader<>(in.lines()));
    }

    private Optional<URL> loadPath(String path, ClassLoader loader){
        URL url = loader.getResource(path);
        return Optional.ofNullable(url);
    }
}
