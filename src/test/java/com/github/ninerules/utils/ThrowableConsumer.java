package com.github.ninerules.utils;

@FunctionalInterface
public interface ThrowableConsumer<T extends Exception>{
    void consume() throws T;
}
