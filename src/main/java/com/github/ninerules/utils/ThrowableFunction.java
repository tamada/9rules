package com.github.ninerules.utils;

@FunctionalInterface
public interface ThrowableFunction<V, R, E extends Exception> {
    R apply(V value) throws E;
}
