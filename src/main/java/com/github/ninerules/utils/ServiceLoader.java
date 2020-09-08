package com.github.ninerules.utils;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

public class ServiceLoader<T> {
    private final List<String> list;

    ServiceLoader(Stream<String> stream){
        list = stream.collect(toList());
    }

    public Stream<Class<T>> stream(){
        return list.stream()
                .map(this::toClass)
                .flatMap(Either::stream);
    }

    @SuppressWarnings("unchecked")
    private Either<Exception, Class<T>> toClass(String className){
        try{
            return Either.ofValue((Class<T>)Class.forName(className));
        } catch(Exception e) {
            return Either.ofException(e);
        }
    }
}
