package com.github.ninerules.utils;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

public class ServiceLoader<T> {
    private List<String> list;

    ServiceLoader(Stream<String> stream){
        list = stream.collect(toList());
    }

    public Stream<Class<T>> stream(){
        return list.stream()
                .map(className -> toClass(className));
    }

    @SuppressWarnings("unchecked")
    private Class<T> toClass(String className){
        return (Class<T>) ExceptionHandler.perform(className, null,
                (item) -> Class.forName(item));
    }
}
