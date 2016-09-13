package com.github.ninerules.utils;

@FunctionalInterface
public interface ThrowableBiFunction<V1, V2, R, E extends Exception> {
    R apply(V1 value1, V2 value2) throws E;
}
