package com.github.ninerules.utils;

@FunctionalInterface
public interface ThrowableFunction<T, K, E extends Exception> {
    K apply(T value) throws E;
}
