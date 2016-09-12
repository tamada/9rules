package com.github.ninerules.utils;

@FunctionalInterface
public interface ThrowableBiFunction<T, V, K, E extends Exception> {
    K apply(T value1, V value2) throws E;
}
