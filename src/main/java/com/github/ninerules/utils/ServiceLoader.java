package com.github.ninerules.utils;

import io.vavr.control.Try;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ServiceLoader<T> {
    private List<String> list;

    ServiceLoader(Stream<String> stream){
        list = stream.collect(toList());
    }

    public Stream<Class<T>> stream(){
        return optionalGet(list.stream()
                .map(this::toClass));
    }

    private <K> Stream<K> optionalGet(Stream<Optional<K>> optionalStream) {
        return optionalStream.filter(Optional::isPresent)
                .map(Optional::get);
    }

    @SuppressWarnings("unchecked")
    private Optional<Class<T>> toClass(String className){
        return Try.of(
                () -> (Class<T>)Class.forName(className))
                .toJavaOptional();
    }
}
